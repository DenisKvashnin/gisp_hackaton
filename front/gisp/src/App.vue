<template>
    <Header />

    <router-view />

    <teleport to="body">
        <div aria-live="assertive" class="z-20 fixed inset-0 flex items-end px-4 py-6 pointer-events-none sm:p-6 sm:items-start">
            <div class="w-full flex flex-col items-center space-y-4 sm:items-end">
                <transition enter-active-class="transform ease-out duration-300 transition" enter-from-class="translate-y-2 opacity-0 sm:translate-y-0 sm:translate-x-2" enter-to-class="translate-y-0 opacity-100 sm:translate-x-0" leave-active-class="transition ease-in duration-100" leave-from-class="opacity-100" leave-to-class="opacity-0">
                    <div v-if="notificationShow" class="max-w-sm w-full bg-white shadow-lg rounded-lg pointer-events-auto ring-1 ring-black ring-opacity-5 overflow-hidden">
                        <div class="p-4">
                            <div class="flex items-center">
                                <div class="w-0 flex-1 flex justify-between">
                                    <p class="w-0 flex-1 text-sm font-medium text-gray-900">
                                        {{ notificationText }}
                                    </p>
                                </div>
                                <div class="ml-4 flex-shrink-0 flex">
                                    <button @click="notificationShow = false" class="bg-white rounded-md inline-flex text-gray-400 hover:text-gray-500 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500">
                                        <span class="sr-only">Close</span>
                                        <XIcon class="h-5 w-5" aria-hidden="true" />
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </transition>
            </div>
        </div>
    </teleport>
</template>

<script>
import Header from "./components/Header.vue";
import { XIcon } from "@heroicons/vue/solid"
import { notificationBus } from "./event-emitter";

export default {
    name: "Home",
    components: { Header, XIcon },
    data: () => ({
        notificationShow: false,
        notificationText: ""
    }),
    created() {
        const saveAuthSession = localStorage.getItem("session");
        if (saveAuthSession) {
            this.$store.dispatch("login");
        }

        notificationBus.on("show", text => {
            this.notificationText = text;
            this.notificationShow = true;

            setTimeout(() => this.notificationShow = false, 1500)
        })
    }
};
</script>

<style src="@vueform/multiselect/themes/default.css"></style>

