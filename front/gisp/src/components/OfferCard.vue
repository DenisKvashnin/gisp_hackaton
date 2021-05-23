<template>
    <div class="flex mt-4 rounded-sm border flex-col" :class="{
        'bg-blue-50 border-blue-200': checked,
        'bg-white border-gray-200': !checked
    }">
        <div class="flex p-4 mb-12">
            <input
                v-model="checked"
                v-bind:disabled = "disableCheckbox"
                type="checkbox"
                class="h-6 w-6 text-blue-600 focus:ring-transparent border-gray-300 rounded disabled"
            />
            <div>
                <p class="font-semibold ml-4 text-lg" :class="{'text-blue-900': checked}">
                    {{ offer.smallName }}
                </p>
                <div class="ml-8 mt-4 flex justify-start items-start">
                    <ChatIcon class="h-5 text-blue-600 flex-none"  />
                    <span class="ml-2 overflow-dots">{{ offer.applianceId }}</span>
                </div>
                <div class="ml-8 mt-4 flex items-center justify-start">
                    <OfficeBuildingIcon class="h-5 text-blue-600" />
                    <span class="ml-2 overflow-dots ">{{ offer.territorialLevel }}</span>
                </div>
            </div>
        </div>

        <div class="inset-0 flex items-center" aria-hidden="true">
            <div class="w-full border-t border-gray-300" />
        </div>

        <div class="flex-grow px-4 py-2" :class="{
            'text-blue-900': checked,
            'text-gray-900': !checked
        }">
            <div class="flex items-center ml-8">
                <EmployeeAmountBadge :type="2" no-bg />

                <div class="ml-12 flex items-center">
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
    ExternalLinkIcon
} from "@heroicons/vue/outline"

export default {
    name: "OfferCard",
    components: {
        EmployeeAmountBadge,
        SwitchHorizontalIcon,
        CalendarIcon,
        ChatIcon,
        LinkIcon,
        TrendingUpIcon,
        OfficeBuildingIcon,
        ExternalLinkIcon
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
        },
        offersToCompare: {
          type: Object,
          required: false
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
        disableCheckbox: function() {
          return this.offersToCompare?.length > 1 && !this.checked
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
    display: -webkit-box;
    -webkit-line-clamp: 3;
    -webkit-box-orient: vertical;
    overflow: hidden;
}
.break {
  flex-basis: 100%;
  height: 0;
}
</style>
