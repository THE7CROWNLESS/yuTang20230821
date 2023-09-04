const state = {
    meal: {}
}

const mutations = {
    SET_EDIT_MEAL(state, meal) {
        state.meal = meal
    },

}

const actions = {

}

export default {
    namespaced: true,
    state,
    mutations,
    actions
}
