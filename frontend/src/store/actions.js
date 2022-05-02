import $axios from "axios";

export async function signupDuplicateEmail({ commit }, payload) {
  const url = 'members/check/email'
  const body = payload
  await $axios.post(url, body)
    .then((res) => {
      if (res.data.code == 200) {
        commit("SIGNUP_DUPLICATE_EMAIL", 1) 
        alert('사용 가능한 이메일입니다.');
      } else {
        commit("SIGNUP_DUPLICATE_EMAIL", 0)
        alert('이미 존재하는 이메일입니다.');
      }
    })
    .catch(() => {
      commit("SIGNUP_DUPLICATE_EMAIL", 0)
      alert('이미 존재하는 이메일입니다.');
    });
}


export async function signupDuplicateNickname({ commit }, payload) {
  const url = 'members/check/nickname'
  const body = payload
  await $axios.post(url, body)
    .then((res) => {
      if (res.data.code == 200) {
        commit("SIGNUP_DUPLICATE_NICKNAME", 1)
        alert('사용 가능한 닉네임입니다.');
      } else {
        commit("SIGNUP_DUPLICATE_NICKNAME", 0)
        alert('이미 존재하는 닉네임입니다.');
      }
    })
    .catch(() => {
      commit("SIGNUP_DUPLICATE_NICKNAME", 0)
      alert('이미 존재하는 닉네임입니다.');
    });
}


export async function signup({ state }, payload) {
  const url = 'members/signup'
  const body = payload
  console.log(state)
  await $axios.post(url, body)
    .then((res) => {
      localStorage.setItem("jwt", res.data.accessToken)
    })
    .catch((err) => {
      console.log(err)
    });
}

// Header랑 Body 동시 송출 방법 *******
export async function signupSecond({ commit }, payload) {
  const url = 'members/signup'
  const header = localStorage.getItem("jwt")
  const body = payload
  await $axios.put(url, body, {
    headers: {
      Authorization: "Bearer " + header,
    },
    })
    .then((res) => {
      console.log(res)
      commit("USER_INFO", res.data)
      commit("LOGIN_STATUS", true)
    })
    .catch((err) => {
      console.log(err)
    });
}

export async function login({ commit }, payload) {
  const url = 'members/signin'
  const body = payload
  await $axios.post(url, body)
    .then((res) => {
      localStorage.setItem("jwt", res.data.accessToken)
      console.log(res)
      commit("USER_INFO", res.data)
      commit("LOGIN_STATUS", true)
    })
    .catch((err) => {
      console.log(err)
    })
}

export async function getUserInfo({ state }, payload) {
  const memberId = payload.memberId
  const jwt = payload.jwt
  const url = `profiles/${memberId}`
  console.log(state)
  await $axios.get(url, {
    headers: {
      Authorization: "Bearer " + jwt,
    },
  })
    .then((res) => {
      console.log(res)
    })
    .catch ((err) => {
      console.log(err)
    })
}