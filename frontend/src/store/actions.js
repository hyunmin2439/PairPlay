import $axios from "axios";

export async function loginGetEmail({ state }, payload) {
  const url = 'members/check/email'
  const body = payload
  console.log(state)
  console.log(body, 'body')
  await $axios.post(url, body)
    .then((res) => {
      console.log(res);
    })
    .catch((err) => {
      console.log(err);
    });
}
