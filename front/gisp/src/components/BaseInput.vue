<template>
    <div>
        <label v-if="label" :for="name" class="block text-sm font-medium text-gray-700 mb-1">
            {{ label }}
        </label>
        <input
            type="text"
            :name="name"
            :id="name"
            class="shadow-sm focus:ring-indigo-500 focus:border-indigo-500 block w-full sm:text-sm border-gray-300 rounded-md"
            :placeholder="placeholder"
            :value="modelValue"
            @change="$emit('update:modelValue', $event.target.value)"
        />
    </div>
</template>

<script>
export default {
    name: "BaseInput",
    emits: ["update:modelValue"],
    inheritAttrs: false,
    props: {
        label: {
            type: String,
            required: false
        },
        name: {
            type: String,
            required: true
        },
        modelValue: {
            type: String,
            required: true
        },
        placeholder: {
            type: String,
            required: false
        }
    }
};
</script>
