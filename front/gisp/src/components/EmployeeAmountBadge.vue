<template>
    <div
        class="rounded-full py-1 px-2 inline-block"
        :class="{
            'bg-blue-700 text-white': !noBg
        }"
    >
        <div class="flex items-center" v-if="type === 1">
            <UserIcon class="h-5" />
            <span class="ml-2 font-medium">1-100</span>
        </div>

        <div class="flex items-center" v-if="type === 2">
            <UsersIcon class="h-5" />
            <span class="ml-2 font-medium">100-250</span>
        </div>

        <div class="flex items-center" v-if="type === 3">
            <UserGroupIcon class="h-5" />
            <span class="ml-2 font-medium">> 251</span>
        </div>
    </div>
</template>

<script>
import { UserIcon, UsersIcon, UserGroupIcon } from "@heroicons/vue/outline"

export default {
    name: "EmployeeAmountBadge",
    components: {
        UserIcon,
        UsersIcon,
        UserGroupIcon,
    },
    props: {
        type: {
            type: Number,
            required: true
        },
        noBg: {
            type: Boolean,
            required: false,
            default: false
        }
    }
};
</script>

<style scoped>

</style>
