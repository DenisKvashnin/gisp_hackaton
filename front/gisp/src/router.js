import Home from "./pages/Home.vue";
import { createRouter, createWebHistory } from "vue-router";
import CreateAndEditOrganizationPage from "./pages/CreateAndEditOrganizationPage.vue";
import OrganizationPage from "./pages/OrganizationPage.vue";

const routes = [
    {
        path: "/",
        component: Home
    },
    {
        path: "/organization/new",
        component: CreateAndEditOrganizationPage
    },
    {
        path: "/organization/:inn",
        component: OrganizationPage
    },
    {
        path: "/organization/edit/:inn",
        component: CreateAndEditOrganizationPage
    }
];

export const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    scrollBehavior(to, from, savedPosition) {
        return { top: 0 }
    },
    routes
});
