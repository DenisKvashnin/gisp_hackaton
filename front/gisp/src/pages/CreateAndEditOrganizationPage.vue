<template>
    <form class="max-w-7xl mx-auto py-6">
        <div class="space-y-8 divide-y divide-gray-200">
            <div class="space-y-8 divide-y divide-gray-200 sm:space-y-5">
                <div>
                    <h3 class="text-lg leading-6 font-medium text-gray-900">
                        {{ inn ? "Редактирование" : "Новое предприятие" }}
                    </h3>
                </div>
            </div>

            <div class="space-y-6 sm:space-y-5">
                <!--Название-->
                <LabelsOnLeftInput v-model="company.full_name" name="full-name" label="Название" />

                <!--Сокращение-->
                <LabelsOnLeftInput v-model="company.name" name="name" label="Сокращение" />

                <!--ИНН-->
                <LabelsOnLeftInput v-model="company.inn" name="inn" label="ИНН" />

                <!--ОГРН-->
                <LabelsOnLeftInput v-model="company.ogrn" name="orgn" label="ОГРН" />

                <!--Размер производства-->
                <div class="sm:grid sm:grid-cols-3 sm:gap-4 sm:items-start sm:border-t sm:border-gray-200 sm:pt-5">
                    <label for="country" class="block text-sm font-medium text-gray-700 sm:mt-px sm:pt-2">
                        Размер производства
                    </label>
                    <div class="mt-1 sm:mt-0 sm:col-span-2">
                        <select id="country" name="country" autocomplete="country" class="max-w-lg block focus:ring-indigo-500 focus:border-indigo-500 w-full shadow-sm sm:max-w-xs sm:text-sm border-gray-300 rounded-md">
                            <option>Малое</option>
                            <option>Среднее</option>
                            <option>Крупное</option>
                        </select>
                    </div>
                </div>
            </div>
        </div>

        <div class="divide-y divide-gray-200 pt-8 space-y-6 sm:pt-10 sm:space-y-5">
            <div>
                <h3 class="text-lg leading-6 font-medium text-gray-900">
                    Дополнительно:
                </h3>
                <p class="mt-1 max-w-2xl text-sm text-gray-500">
                    Данные для более точного поиска
                </p>
            </div>

            <div class="space-y-6 sm:space-y-5">
                <LabelsOnLeftInput v-model="company.branch" name="branch" label="Отрасль" />

                <!--Регион-->
                <div
                    class="sm:grid sm:grid-cols-3 sm:gap-4 sm:items-start sm:border-t sm:border-gray-200 sm:pt-5"
                >
                    <label class="block text-sm font-medium text-gray-700 sm:mt-px sm:pt-2">
                        Регион
                    </label>
                    <div class="mt-1 sm:mt-0 sm:col-span-2 w-80">
                        <Multiselect
                            noResultsText="Не найдено"
                            class="shadow-sm"
                            v-model="company.region"
                            :options="regionOptions"
                            searchable
                        />
                    </div>
                </div>

                <!--Организационно-правовая форма-->
                <LabelsOnLeftInput v-model="company.legal_org_form" name="legal_org_form" label="Организационно-правовая форма" />

                <!--Отрасли-->

                <!--Дата регистрации-->
                <LabelsOnLeftInput
                    v-model="company.date_reg_with_tax_authority"
                    name="date_reg_with_tax_authority"
                    label="Дата регистрации"
                />

                <!--Адрес-->
                <LabelsOnLeftInput v-model="company.fact_address" name="fact_address" label="Адрес" />

                <!--Сайт-->
                <LabelsOnLeftInput v-model="company.address_site" name="address_site" label="Сайт" />

                <div
                    class="sm:grid sm:grid-cols-3 sm:gap-4 sm:items-start sm:border-t sm:border-gray-200 sm:pt-5"
                >
                    <label class="block text-sm font-medium text-gray-700 sm:mt-px sm:pt-2">
                        Особая экономическая зона
                    </label>
                    <div class="mt-1 sm:mt-0 sm:col-span-2">
                        <SwitchGroup as="div" class="flex items-center">
                            <Switch v-model="enabled" :class="[enabled ? 'bg-blue-600' : 'bg-gray-200', 'relative inline-flex flex-shrink-0 h-6 w-11 border-2 border-transparent rounded-full cursor-pointer transition-colors ease-in-out duration-200 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500']">
                                <span aria-hidden="true" :class="[enabled ? 'translate-x-5' : 'translate-x-0', 'pointer-events-none inline-block h-5 w-5 rounded-full bg-white shadow transform ring-0 transition ease-in-out duration-200']" />
                            </Switch>
                        </SwitchGroup>
                    </div>
                </div>
            </div>
        </div>

        <div class="pt-5">
            <div class="flex justify-end">
                <BaseButton secondary @click="$router.push('/')">Отмена</BaseButton>
                <BaseButton primary class="ml-3" @click="saveOrUpdateCompany">Сохранить</BaseButton>
            </div>
        </div>
    </form>
</template>

<script>
import Multiselect from "@vueform/multiselect";
import LabelsOnLeftInput from "../components/LabelsOnLeftInput.vue";
import { httpClient } from "../http-client";
import { SwitchGroup, SwitchLabel, Switch } from "@headlessui/vue"
import BaseButton from "../components/BaseButton.vue";
import { notificationBus } from "../event-emitter";

export default {
    name: "CreateAndEditOrganizationPage",
    components: {
        BaseButton,
        LabelsOnLeftInput,
        Multiselect,
        SwitchGroup,
        SwitchLabel,
        Switch
    },
    data: () => ({
        inn: null,

        value: null,
        enabled: false,
        regionOptions: [],
        okvedOptions: [],

        company: {
            full_name: "",
            email: null,
            electronic_trading_platforms: null,
            okved2: [],
            type_organization: null,
            kind_of_activity_tass: null,
            kind_of_activity_dop_tass: null,
            legal_org_form: "",
            type_company: null,
            status_company: null,
            date_reg_with_tax_authority: "",
            code_tax_authority: null,
            fact_address: "",
            attribute_company: null,
            name: "",
            kind_company: "",
            branch: null,
            ogrn: "",
            inn: "",
            kpp: null,
            region: "",
            address: null,
            contact_email: null,
            address_site: "",
            contact_phone: null
        }
    }),
    created() {
        const { inn } = this.$route.params;

        if (inn) {
            this.inn = inn;
        }
    },
    async mounted() {
        const regions = (await httpClient().get("/region")).data;
        this.regionOptions = regions.map(r => ({
            label: r.name,
            value: r.id
        }));

        if (this.inn) {
            this.company = (await httpClient().get(`/company/findByInn?inn=${this.inn}`)).data;
        }
    },
    methods: {
        async saveOrUpdateCompany() {
            this.company.okved2 = "";

            await httpClient().post("/user/company", this.company);

            if (!this.inn) {
                await httpClient().post("/user/company/saveByInn", this.company.inn);
            }

            notificationBus.emit("show", "Изменения сохранены");

            await this.$router.push("/");
        }
    }
};
</script>

<style>
.multiselect-tag {
    background: #2563EB !important;
}

.multiselect-tag i::before {
    color: white !important;
}
</style>
