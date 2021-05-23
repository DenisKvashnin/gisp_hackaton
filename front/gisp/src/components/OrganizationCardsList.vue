<template>
    <ul class="grid grid-cols-1 gap-6 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4">
        <!--Create card-->
        <li
            class="cursor-pointer p-4 h-48 col-span-1 flex items-center justify-center bg-white rounded-lg shadow divide-y divide-gray-200"
            @click="createModalOpen = true"
        >
            <PlusIcon class="h-24 text-gray-500" />
        </li>

        <!--Create modal-->
        <Modal :show="createModalOpen">
            <div class="flex items-start">
                <div class="w-full">
                    <BaseInput
                        v-model="newOrganizationInn"
                        name="itm"
                        label="ИНН / ОГРН"
                        placeholder="123456789"
                    />

                    <p class="cursor-pointer flex items-center justify-end mt-2">
                        или <router-link to="/organization/new" class="font-bold text-blue-700 ml-1">ввести вручную</router-link>
                        <ArrowRightIcon class="h-4 text-blue-700 ml-1" />
                    </p>
                </div>
            </div>
            <div class="mt-32 flex flex-row-reverse">
                <BaseButton primary @click="addOrganizationByInn" :disabled="isFormSubmitting">Добавить</BaseButton>
                <BaseButton class="mr-2" secondary @click="createModalOpen = false" :disabled="isFormSubmitting">Отмена</BaseButton>
            </div>
        </Modal>

        <!--Organization cards-->
        <router-link
            v-for="organization in organizations"
            :key="organization"
            tag="li"
            :to="`/organization/${organization.inn}`"
            class="flex flex-col justify-between items-start font-bold cursor-pointer pt-4 h-48 col-span-1 bg-white rounded-lg shadow"
        >
            <div>
                <h3 class="text-sm capitalize text-center px-4">
                    {{ organization.full_name.toLowerCase() }}
                </h3>

                <div v-if="organizationDataNotFulfilled(organization)" class="text-center w-full mt-2">
                    <span class="inline-flex items-center px-2.5 py-0.5 rounded-full text-xs font-medium bg-yellow-100 text-yellow-800">
                        Заполните профиль
                    </span>
                </div>
            </div>

            <!--Card footer-->
            <div class="flex items-center justify-between w-full border-t border-gray-300 py-2">
                <!--Type-->
                <EmployeeAmountBadge v-if="organization.type" :type="organization.type" no-bg class="w-full text-sm"  />

                <PencilAltIcon
                    class="h-7 border-l border-r border-gray-300 w-full text-gray-500 hover:text-gray-900"
                    @click.prevent="$router.push(`/organization/edit/${organization.inn}`)"
                />

                <XCircleIcon
                    class="h-7 w-full text-gray-500 hover:text-gray-900"
                    @click.prevent="deleteOrganization(organization)"
                />
            </div>
        </router-link>
    </ul>
</template>

<script>
import { PlusIcon, PencilAltIcon, XCircleIcon } from "@heroicons/vue/outline"
import { ArrowRightIcon } from "@heroicons/vue/solid"
import BaseButton from "./BaseButton.vue";
import Modal from "./Modal.vue";
import BaseInput from "./BaseInput.vue";
import { httpClient } from "../http-client";
import { notificationBus } from "../event-emitter";
import EmployeeAmountBadge from "./EmployeeAmountBadge.vue";

export default {
    name: "OrganizationCardsList",
    components: {
        EmployeeAmountBadge,
        BaseInput,
        Modal,
        BaseButton,
        PlusIcon,
        ArrowRightIcon,
        PencilAltIcon,
        XCircleIcon
    },
    data: () => ({
        organizations: [],
        createModalOpen: false,
        newOrganizationInn: "",
        isFormSubmitting: false,
        notificationShow: false,
        notificationText: "",
    }),
    mounted() {
        this.loadOrganizations();
    },
    methods: {
        async loadOrganizations() {
            const [organizationsResponse, countResponse] = await Promise.all([
                httpClient().get("/user/company"),
                httpClient().get("/user/count")
            ])

            const usersCountData = countResponse.data;

            this.organizations = organizationsResponse.data.map(o => {
                const usersCountEntry = usersCountData.find(u => u.inn === o.inn);

                let organizationType = 0;

                if (usersCountEntry) {
                    const usersCount = parseInt(usersCountEntry.peopleCount);
                    if (usersCount > 0 && usersCount < 100) {
                        organizationType = 1;
                    } else if (usersCount < 251) {
                        organizationType = 2;
                    } else {
                        organizationType = 3;
                    }
                }

                return {
                    ...o,
                    type: organizationType
                }
            })
        },
        async addOrganizationByInn() {
            this.isFormSubmitting = true;

            try {
                await httpClient().post("/user/company/saveByInn", this.newOrganizationInn);

                await this.loadOrganizations();

                this.createModalOpen = false;
                this.newOrganizationInn = "";
            } catch (e) {
                console.error(e)

                notificationBus.emit("show", `Не удалось найти предприятие по ИНН ${this.newOrganizationInn}`);
            } finally {
                this.isFormSubmitting = false;
            }
        },
        async deleteOrganization(organization) {
            await httpClient().delete("/user/company", {
                data: organization
            });
            this.organizations = this.organizations.filter(o => o.inn !== organization.inn);
        },
        organizationDataNotFulfilled(organization) {
            return !organization.branch ||
                !organization.region ||
                !organization.legal_org_form ||
                !organization.date_reg_with_tax_authority ||
                !organization.fact_address ||
                !organization.address_site;
        }
    }
}
</script>
