// import state from "./state";

export function SIGNUP_DUPLICATE_EMAIL(state, data) {
  state.emailDuplicate = data
}

export function SIGNUP_DUPLICATE_NICKNAME(state, data) {
  state.nicknameDuplicate = data
}

export function LOGIN_STATUS(state, data) {
  state.loginStatus = data
}