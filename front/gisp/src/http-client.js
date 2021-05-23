import axios from "axios";

export function httpClient() {
    return axios.create({
        baseURL: "https://gisp-api.kovalev.team",
        headers: {
            "Content-Type": "application/json"
        }
    })
}
