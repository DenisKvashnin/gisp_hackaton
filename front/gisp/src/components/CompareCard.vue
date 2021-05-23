<template>
    <div class="flex mt-4 rounded-sm border flex-col w-1/2" :class="{
        'bg-blue-50 border-blue-200': checked,
        'bg-white border-gray-200': !checked
    }">
        <p class="font-semibold ml-4 text-lg py-4" :class="{'text-blue-900': checked}">
            {{ offer.smallName }}
        </p>

        <div class="mt-auto">
            <div class="ml-8 mt-6 flex justify-start">
                <ChevronDoubleUpIcon class="h-5 mt-1 text-blue-600 flex-none" />
                <span class="ml-2"><b>Процентная ставка:</b> {{ percent }}</span>
            </div>
            <div class="ml-8 mt-4 flex items-center justify-start">
                <PlusIcon class="h-6 text-blue-600 flex-none" />
                <span class="ml-2"><b>доля софинансирования:</b> {{ sofinance }}</span>
            </div>

            <div class="inset-0 flex items-center mt-8" aria-hidden="true">
                <div class="w-full border-t border-gray-300" />
            </div>

            <div class="flex-grow px-4 py-2" :class="{
            'text-blue-900': checked,
            'text-gray-900': !checked
        }">
                <div class="flex items-center ml-8">

                    <div class=" flex items-center">
                        <component :is="measureIcon" class="h-5" />

                        <span class="ml-2">{{ measureType }}</span>
                    </div>

                    <div v-if="substringAmountOfSupport && substringAmountOfSupport.length <= 30" class="flex items-center ml-12">
                        <img src="../assets/img/rubble-black.svg" >
                        <span class="ml-2">{{ substringAmountOfSupport }}</span>
                    </div>

                    <span class="ml-12 flex items-center">
                    <CalendarIcon class="h-5" />

                    <span class="ml-2">{{ offer.srokVozvrata }}</span>
                </span>

                    <div class="ml-12 flex items-center">
                        <ExternalLinkIcon class="h-5" />
                        <a :href="offer.url" target="_blank" class="ml-2 flex items-center">
                            ГИСП
                        </a>
                    </div>
                </div>
            </div>
        </div>

    </div>
</template>

<script>
import EmployeeAmountBadge from "./EmployeeAmountBadge.vue";
import {
    SwitchHorizontalIcon,
    LinkIcon,
    CalendarIcon,
    ChatIcon,
    TrendingUpIcon,
    OfficeBuildingIcon,
    ExternalLinkIcon,
    ClockIcon,
    ChevronDoubleUpIcon,
    PlusIcon,
    AdjustmentsIcon,
    CreditCardIcon,
} from "@heroicons/vue/outline"

export default {
    name: "CompareCard",
    components: {
        EmployeeAmountBadge,
        SwitchHorizontalIcon,
        CalendarIcon,
        ChatIcon,
        LinkIcon,
        TrendingUpIcon,
        OfficeBuildingIcon,
        ExternalLinkIcon,
        ClockIcon,
        ChevronDoubleUpIcon,
        PlusIcon,
        AdjustmentsIcon,
        CreditCardIcon,
    },
    emits: ["added", "removed"],
    props: {
        offer: {
            type: Object,
            required: true
        },
        measureType: {
            type: String,
            required: true
        }
    },
    data: () => ({
        checked: false
    }),
    computed: {
        substringAmountOfSupport() {
            if (!this.offer.amountOfSupport) {
                return;
            }

            const regex = /от.+до.+млн/gm;
            const result = regex.exec(this.offer.amountOfSupport);

            if (result && result.length) {
                return result[0];
            }

            return "";
        },
        measureIcon() {
            if (this.measureType === "Займ") {
                return SwitchHorizontalIcon;
            }

            if (this.measureType === "Консультация") {
                return ChatIcon;
            }

            return TrendingUpIcon;
        },
        percent() {
            console.log(this.offer.procentVozvrata)
           let replace = this.offer.procentVozvrata?.match(/([\d\s]*%) базовая ставка | ([\d\s]*%)/)
           if (replace){
            return replace[0]
          } else {
            return this.offer.procentVozvrata
          }
        },
        sofinance() {
           console.log(this.offer.dolyaIsofinance)
           let replace = this.offer.dolyaIsofinance?.match(/([\d\s]*%)|([\d\s]*.*?%)|([\d\s]*?.*?процент)/)
           if (replace){
            return replace[0]
          } else {
            return this.offer.dolyaIsofinance
          }
        }
    },
    watch: {
        checked() {
            if (this.checked) {
                this.$emit("added");
            } else {
                this.$emit("removed");
            }
        }
    }
};
</script>

<style>
.overflow-dots {
}
</style>
