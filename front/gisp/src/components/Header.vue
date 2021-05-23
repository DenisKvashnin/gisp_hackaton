<template>
    <nav class="bg-gray-800">
        <div class="max-w-7xl mx-auto px-4">
            <div class="flex items-center justify-between h-16">
                <div class="flex items-center">
                    <!--Logo-->
                    <router-link to="/" class="flex-shrink-0">
                        <img
                            class="w-28"
                            src="../assets/img/logo.png"
                            alt="Workflow"
                        />
                    </router-link>

                    <!--Links-->
                    <div class="ml-10 flex items-baseline space-x-4">
                        <template v-for="(item, itemIdx) in navigation" :key="item">
                            <template v-if="$route.path === item.to">
                                <router-link :to="item.to" class="bg-gray-900 text-white px-3 py-2 rounded-md text-sm font-medium">
                                    {{ item.name }}
                                </router-link>
                            </template>
                            <router-link v-else :to="item.to" class="text-gray-300 hover:bg-gray-700 hover:text-white px-3 py-2 rounded-md text-sm font-medium">
                                {{ item.name }}
                            </router-link>
                        </template>
                    </div>
                </div>

                <!-- Profile dropdown -->
                <Menu v-if="$store.state.isAuthenticated" as="div" class="relative">
                    <div>
                        <MenuButton
                            class="max-w-xs bg-gray-800 rounded-full flex items-center text-sm focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-offset-gray-800 focus:ring-white"
                        >
                            <img
                                class="h-8 w-8 rounded-full object-cover"
                                src="../assets/img/avatar.jpeg"
                                alt="Avatar"
                            />
                            <span class="text-white ml-4">Ковалев Илья</span>
                        </MenuButton>
                    </div>
                    <transition enter-active-class="transition ease-out duration-100" enter-from-class="transform opacity-0 scale-95" enter-to-class="transform opacity-100 scale-100" leave-active-class="transition ease-in duration-75" leave-from-class="transform opacity-100 scale-100" leave-to-class="transform opacity-0 scale-95">
                        <MenuItems class="origin-top-right absolute right-0 mt-2 w-48 rounded-md shadow-lg py-1 bg-white ring-1 ring-black ring-opacity-5 focus:outline-none">
                            <MenuItem  v-slot="{ active }">
                                <a href="#" :class="[active ? 'bg-gray-100' : '', 'block px-4 py-2 text-sm text-gray-700']">
                                    Войти через Госуслуги
                                </a>
                            </MenuItem>

                            <MenuItem  v-slot="{ active }">
                                <span
                                    @click="$store.dispatch('logout')"
                                    :class="[active ? 'bg-red-100' : '', 'block px-4 py-2 text-sm text-red-600 cursor-pointer']"
                                >
                                    Выйти
                                </span>
                            </MenuItem>
                        </MenuItems>
                    </transition>
                </Menu>

                <button
                    v-else
                    type="button"
                    class="inline-flex items-center px-4 py-2 border border-gray-300 shadow-sm text-sm font-medium rounded-md text-gray-700 bg-white hover:bg-gray-50 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500"
                    @click="$store.dispatch('login')"
                >
                    Войти
                </button>
            </div>
        </div>
    </nav>
</template>

<script>
import { Menu, MenuButton, MenuItem, MenuItems } from '@headlessui/vue'
import { SearchIcon } from "@heroicons/vue/outline"
import BaseInput from "./BaseInput.vue";
import BaseButton from "./BaseButton.vue";

export default {
    name: "Header",
    components: {
        BaseButton,
        BaseInput,
        Menu,
        MenuButton,
        MenuItem,
        MenuItems,
        SearchIcon
    },
    data: () => ({
        navigation:  [
            {
                name: "Главная",
                to: "/"
            }
        ]
    })
}
</script>
