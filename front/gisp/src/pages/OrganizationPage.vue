<template>
    <div class="bg-gray-100 min-h-screen">
        <div class="max-w-7xl mx-auto py-6 sm:px-6 lg:px-8">
            <!--Smart filter switch-->
            <template v-if="$store.state.isAuthenticated">
                <SwitchGroup as="div" class="flex items-center mb-4">
                    <Switch v-model="smartSearchToggle" :class="[smartSearchToggle ? 'bg-blue-600' : 'bg-gray-200', 'relative inline-flex flex-shrink-0 h-6 w-11 border-2 border-transparent rounded-full cursor-pointer transition-colors ease-in-out duration-200 focus:outline-none']">
                        <span aria-hidden="true" :class="[smartSearchToggle ? 'translate-x-5' : 'translate-x-0', 'pointer-events-none inline-block h-5 w-5 rounded-full bg-white shadow transform ring-0 transition ease-in-out duration-200']" />
                    </Switch>
                    <SwitchLabel as="span" class="ml-3">
                        <span class="text-lg font-medium text-gray-900">Умный поиск</span>
                    </SwitchLabel>
                </SwitchGroup>
            </template>

            <!--Filters-->
            <div class="flex items-center p-4 col-span-1 bg-white rounded-lg shadow">
                <span class="font-semibold">Сумма</span>, млн:
                <span class="text-blue-600 text-lg mx-2">от</span>

                <div class="w-20">
                    <BaseInput
                        name="start-sum"
                        placeholder="0.23"
                        :model-value="filters.startSum"
                        @update:modelValue="filters.startSum = $event; loadOffers()"
                    />
                </div>

                <span class="text-blue-600 text-lg mx-2">до</span>

                <div class="w-20">
                    <BaseInput
                        name="end-sum"
                        placeholder="100"
                        v-model="filters.endSum"
                        @update:modelValue="filters.endSum = $event; loadOffers()"
                    />
                </div>

                <span class="ml-4 font-semibold">Мера поддержки:</span>
                <select
                    v-model="filters.measureType"
                    @change="loadOffers"
                    class="ml-2 block pl-3 pr-10 py-2 text-base border-gray-300 focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm rounded-md"
                >
                    <option selected value="">-Не выбрано-</option>
                    <option value="Субсидия">Субсидия</option>
                    <option value="Займ">Займ</option>
                    <option value="Консультация">Консультация</option>
                    <option value="Нематериальные">Нематериальные</option>
                </select>

                <template v-if="filters.measureType === 'Займ'">
                <span class="ml-4">
                    <span class="font-semibold">Срок</span>, год:
                </span>
                    <Slider class="w-20 ml-4" v-model="value2" :min="1" :max="5" />

                    <span class="ml-4">
                    <span class="font-semibold">Процент</span>, %
                </span>
                    <Slider class="w-20 ml-4" v-model="value3" :min="1" :max="5" />
                </template>
            </div>

            <!--Advanced filters-->
            <Disclosure v-slot="{ open }">
                <DisclosureButton class="flex mt-6 focus:outline-none w-full">
                    <div class="flex justify-between w-full items-center p-4 col-span-1 bg-white rounded-lg shadow">
                        <span class="text-lg">Расширенный поиск</span>

                        <ChevronRightIcon class="h-6" :class='open ? "transform rotate-90" : ""' />
                    </div>
                </DisclosureButton>
                <transition
                    enter-active-class="transition duration-100 ease-out"
                    enter-from-class="transform scale-95 opacity-0"
                    enter-to-class="transform scale-100 opacity-100"
                    leave-active-class="transition duration-75 ease-out"
                    leave-from-class="transform scale-100 opacity-100"
                    leave-to-class="transform scale-95 opacity-0"
                >
                    <DisclosurePanel class="flex items-start w-full py-8 px-4 col-span-1 bg-white rounded-lg shadow">
                        <div>
                            <div class="flex items-center">
                                <input
                                    v-model="sofinance"
                                    @change="loadOffers"
                                    id="sofinance"
                                    type="checkbox"
                                    class="ml-4 h-4 w-4 text-blue-600 focus:ring-transparent border-gray-300 rounded"
                                />
                                <label for="sofinance" class="ml-2">Софинансирование</label>
                            </div>

                            <div class="flex items-center mt-4">
                                <input id="economic-zone" type="checkbox" class="ml-4 h-4 w-4 text-blue-600 focus:ring-transparent border-gray-300 rounded" />
                                <label for="economic-zone" class="ml-2">Особенная экономическая зона</label>
                            </div>
                        </div>

                        <div class="ml-24">
                            <span>Регион:</span>
                            <select
                                v-model="territorialLevel"
                                class="w-52 block pl-3 pr-10 py-2 text-base border-gray-300 focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm rounded-md"
                            >
                                <option value="" selected>-Не выбрано-</option>
                                <option value="Региональный">Региональный</option>
                                <option value="Федеральный">Федеральный</option>
                            </select>
                        </div>

                        <div class="ml-24">
                            <label class="block font-medium text-gray-700">
                                Отрасль
                            </label>
                            <div class="mt-1 sm:mt-0 sm:col-span-2 w-52">
                                <Multiselect
                                    v-model="selectedBranches"
                                    noResultsText="Не найдено"
                                    class="shadow-sm"
                                    :options="branchOptions"
                                    searchable
                                    mode="multiple"
                                />
                            </div>
                        </div>
                    </DisclosurePanel>
                </transition>
            </Disclosure>

            <!--Offers-->
            <div class="mt-12 flex items-center justify-between">
                <h4 class="text-2xl">Предложения ({{ filteredOffers.length }}):</h4>
                <BaseButton primary v-if="offersToCompare.length > 1" @click="createCompareOpen = true">Сравнить</BaseButton>
            </div>

            <Compare :show="createCompareOpen">
                <div class="w-full flex items-start">
                    <div class="w-full flex">
                      <CompareCard
                          v-for="offer in offers.filter(o => offersToCompare.includes(o.id) )"
                          :key="offer.id"
                          :offer="offer"
                          :measure-type="filters.measureType"
                          class="mx-4"
                      />
                    </div>
                </div>
                <div class="mt-6 flex flex-row-reverse">
                    <BaseButton class="mr-2" secondary @click="createCompareOpen = false" :disabled="isFormSubmitting">Закрыть</BaseButton>
                </div>
            </Compare>

            <div v-if="isLoading" class="loader"></div>

            <div v-else-if="!filteredOffers.length" class="mt-2">Предложений не найдено</div>

            <template v-else>
                <OfferCard
                    v-for="offer in filteredOffers"
                    :key="offer.id"
                    :offer="offer"
                    :measure-type="filters.measureType"
                    @added="offersToCompare.push(offer.id)"
                    :offersToCompare="offersToCompare"
                    @removed="offersToCompare = offersToCompare.filter(o => o !== offer.id)"
                />
            </template>
        </div>
    </div>
</template>

<script>
import Slider from "@vueform/slider";
import { Disclosure, DisclosureButton, DisclosurePanel, Switch, SwitchGroup, SwitchLabel } from "@headlessui/vue";
import { ChevronRightIcon } from "@heroicons/vue/solid";
import Multiselect from "@vueform/multiselect";
import { httpClient } from "../http-client";
import OfferCard from "../components/OfferCard.vue";
import CompareCard from "../components/CompareCard.vue";
import BaseButton from "../components/BaseButton.vue";
import BaseInput from "../components/BaseInput.vue";
import Compare from "../components/Compare.vue";
import { notificationBus } from "../event-emitter";

export default {
    name: "OrganizationPage",
    components: {
        BaseInput,
        BaseButton,
        OfferCard,
        CompareCard,
        Slider,
        Disclosure,
        DisclosureButton,
        DisclosurePanel,
        ChevronRightIcon,
        Multiselect,
        SwitchGroup,
        Switch,
        Compare,
        SwitchLabel
    },
    data: () => ({
        type: 1,
        value2: [1, 2],
        value3: [1, 2],
        selectValue: [],

        smartSearchToggle: false,
        areFiltersLocked: false,
        isLoading: false,
        createCompareOpen: false,
        territorialLevel: "",
        sofinance: false,
        branchOptions: [],
        selectedBranches: [],
        filters: {
            startSum: "",
            endSum: "",
            measureType: "",
            srokSubsidy: null,
            procentSubsidy: null,
            srokVozvrata: null,
            startPprocentVozvrata: null,
            endProcentVozvrata: null,
            isSofinance: "false",
            ids: []
        },

        offers: [],
        offersToCompare: []
    }),
    created() {
        this.smartSearchToggle = this.areFiltersLocked = this.$store.state.isAuthenticated;

        this.$store.subscribe((mutation, state) => {
            if (mutation.type === "setIsAuthenticated") {
                this.smartSearchToggle = state.isAuthenticated;
            }
        })
    },
    mounted() {
        this.loadOffers();
        this.loadBranchOptions();
        if (!this.$store.state.isAuthenticated){
          notificationBus.emit("show", `авторизуйтесь, Что бы воспользоваться умным поиском`);
        }
    },
    methods: {
        async loadOffers() {
            this.isLoading = true;

            if (this.smartSearchToggle && !this.filters.ids.length) {
                const { inn } = this.$route.params;
                const data = (await httpClient().get(`https://gisp-collab.kovalev.team/cosine_rec?q=${inn}`)).data;

                if (!data) {
                    this.offers = [];
                    this.isLoading = false;

                    return;
                }

                this.filters.ids = data.split("-").map(s => parseInt(s));
            }

            if (!this.smartSearchToggle) {
                this.filters.ids = [];
            }

            this.filters.isSofinance = this.sofinance.toString();

            this.offers = (await httpClient().post("/support/offer/filter", this.filters)).data;
            this.offersToCompare = []
            this.isLoading = false;
        },
        loadBranchOptions() {
            httpClient().get("/branch").then(({ data: branches }) => {
                this.branchOptions = branches.map(b => b.name);
            })
        }
    },
    watch: {
        smartSearchToggle() {
            this.loadOffers();
        }
    },
    computed: {
        filteredOffers() {
            if (!this.offers || !this.offers.length) {
                return [];
            }

            let offers = this.offers;

            if (this.selectedBranches.length) {
                offers = offers
                    .filter(o => this.selectedBranches.some(branch => o.applianceId?.includes(branch)))
            }

            if (this.territorialLevel) {
                offers = offers
                    .filter(o => o.territorialLevel === this.territorialLevel);
            }

            return offers;
        }
    }
  };
</script>

<style src="@vueform/slider/themes/default.css"></style>

<style>
.slider-tooltip, .slider-connect {
    background: #2563EB;
    border-color: #2563EB;
}

.slider-tooltip {
    opacity: 0;
    transition: opacity ease-in-out 0.2s;
}

.slider-base:hover .slider-tooltip {
    opacity: 1;
}

.loader,
.loader:before,
.loader:after {
    background: #2563eb;
    -webkit-animation: load1 1s infinite ease-in-out;
    animation: load1 1s infinite ease-in-out;
    width: 1em;
    height: 4em;
}
.loader {
    color: #2563eb;
    text-indent: -9999em;
    margin: 88px auto;
    position: relative;
    font-size: 11px;
    -webkit-transform: translateZ(0);
    -ms-transform: translateZ(0);
    transform: translateZ(0);
    -webkit-animation-delay: -0.16s;
    animation-delay: -0.16s;
}
.loader:before,
.loader:after {
    position: absolute;
    top: 0;
    content: '';
}
.loader:before {
    left: -1.5em;
    -webkit-animation-delay: -0.32s;
    animation-delay: -0.32s;
}
.loader:after {
    left: 1.5em;
}
@-webkit-keyframes load1 {
    0%,
    80%,
    100% {
        box-shadow: 0 0;
        height: 4em;
    }
    40% {
        box-shadow: 0 -2em;
        height: 5em;
    }
}
@keyframes load1 {
    0%,
    80%,
    100% {
        box-shadow: 0 0;
        height: 4em;
    }
    40% {
        box-shadow: 0 -2em;
        height: 5em;
    }
}

.multiselect-option {
    min-height: 92px !important;
}
</style>
