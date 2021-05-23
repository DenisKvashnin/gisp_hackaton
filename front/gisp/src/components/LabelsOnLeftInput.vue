<template>
    <div
        class="sm:grid sm:grid-cols-3 sm:gap-4 sm:items-start sm:border-t sm:border-gray-200 sm:pt-5"
    >
        <label :for="name" class="block text-sm font-medium text-gray-700 sm:mt-px sm:pt-2">
            {{ label }}
        </label>
        <div class="mt-1 sm:mt-0 sm:col-span-2">
            <input
                type="text"
                :name="name"
                :id="name"
                class="max-w-lg block w-full shadow-sm focus:ring-indigo-500 focus:border-indigo-500 sm:max-w-xs sm:text-sm border-gray-300 rounded-md"
                :placeholder="placeholder"
                :value="modelValue"
                @input="$emit('update:modelValue', $event.target.value)"
            />
        </div>
    </div>
</template>

<script>
export default {
    name: "LabelsOnLeftInput",
    emits: ["update:modelValue"],
    props: {
        name: {
            type: String,
            required: true
        },
        placeholder: {
            type: String,
            required: false
        },
        label: {
            type: String,
            required: true
        },
        modelValue: {
            type: String,
            required: true
        },
    }
};
</script>

<style scoped>

</style>
