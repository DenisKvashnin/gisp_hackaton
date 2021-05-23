import { createStore } from 'vuex'

export const store = createStore({
    state () {
        return {
            isAuthenticated: false
        }
    },
    mutations: {
        setIsAuthenticated(state, newValue) {
            state.isAuthenticated = newValue;
        }
    },
    actions: {
        logout(context) {
            context.commit("setIsAuthenticated", false);
            localStorage.removeItem("session");
        },
        login(context) {
            context.commit("setIsAuthenticated", true);
            localStorage.setItem("session", "true");
        }
    }
})
