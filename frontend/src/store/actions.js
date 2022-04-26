import $axios from "axios";

export async function AccountsLogin(payload) {
  const url = './login'
  const body = payload
  await $axios.get(url, body)
    .then((res) => {
      console.log(res)
    })
    .catch((err) => {
      console.log(err)
    })
}

