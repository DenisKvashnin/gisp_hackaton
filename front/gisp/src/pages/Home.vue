<template>
    <section class="bg-white shadow">
        <div class="max-w-7xl mx-auto py-6 px-4 sm:px-6 lg:px-8">
            <h1 class="text-2xl font-bold text-gray-900">Мои предприятия</h1>
        </div>
    </section>

    <main class="bg-gray-100 min-h-screen">
        <div class="max-w-7xl mx-auto py-6 sm:px-6 lg:px-8">
            <OrganizationCardsList />
        </div>
    </main>
</template>

<script>
import OrganizationCardsList from "../components/OrganizationCardsList.vue";

export default {
    name: "Home",
    components: { OrganizationCardsList }
};
</script>
