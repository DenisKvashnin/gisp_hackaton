<template>
    <button
        type="button"
        class="mt-3 w-full inline-flex justify-center rounded-md border shadow-sm px-4 py-2 text-base font-medium sm:mt-0 sm:w-auto sm:text-sm"
        :class="{
            'border-transparent bg-blue-600 text-white': primary && !disabled,
            'border-gray-300 bg-white text-gray-700': secondary && !disabled,
            'border-transparent bg-gray-300 text-gray-500 cursor-not-allowed': primary && disabled,
            'border-gray-300 bg-gray-100 text-gray-500 cursor-not-allowed': secondary && disabled
        }"
        @click="$emit('click')"
    >
        <slot />
    </button>
</template>

<script>
export default {
    name: "BaseButton",
    emits: ["click"],
    props: {
        primary: {
            type: Boolean,
            required: false,
            default: false
        },
        secondary: {
            type: Boolean,
            required: false,
            default: false
        },
        disabled: {
            type: Boolean,
            required: false,
            default: false
        }
    }
}
</script>
