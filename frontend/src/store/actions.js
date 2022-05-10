import $axios from "axios";

export async function signupDuplicateEmail({ commit }, payload) {
  const url = "members/check/email";
  const body = payload;
  await $axios
    .post(url, body)
    .then((res) => {
      if (res.data.code == 200) {
        commit("SIGNUP_DUPLICATE_EMAIL", 1);
        alert("사용 가능한 이메일입니다.");
      } else {
        commit("SIGNUP_DUPLICATE_EMAIL", 0);
        alert("이미 존재하는 이메일입니다.");
      }
    })
    .catch(() => {
      commit("SIGNUP_DUPLICATE_EMAIL", 0);
      alert("이미 존재하는 이메일입니다.");
    });
}

export async function signupDuplicateNickname({ commit }, payload) {
  const url = "members/check/nickname";
  const body = payload;
  await $axios
    .post(url, body)
    .then((res) => {
      if (res.data.code == 200) {
        commit("SIGNUP_DUPLICATE_NICKNAME", 1);
        alert("사용 가능한 닉네임입니다.");
      } else {
        commit("SIGNUP_DUPLICATE_NICKNAME", 0);
        alert("이미 존재하는 닉네임입니다.");
      }
    })
    .catch(() => {
      commit("SIGNUP_DUPLICATE_NICKNAME", 0);
      alert("이미 존재하는 닉네임입니다.");
    });
}

export async function signup({ state }, payload) {
  const url = "members/signup";
  const body = payload;
  console.log(state);
  await $axios
    .post(url, body)
    .then((res) => {
      localStorage.setItem("jwt", res.data.accessToken);
    })
    .catch((err) => {
      console.log(err);
    });
}

// Header랑 Body 동시 송출 방법 *******
export async function signupSecond({ commit }, payload) {
  const url = "members/signup";
  const header = localStorage.getItem("jwt");
  const body = payload;
  await $axios
    .put(url, body, {
      headers: {
        Authorization: "Bearer " + header,
      },
    })
    .then((res) => {
      commit("USER_INFO", res.data);
      commit("LOGIN_STATUS", true);
    })
    .catch((err) => {
      console.log(err);
    });
}

export async function login({ commit }, payload) {
  const url = "members/signin";
  const body = payload;
  await $axios
    .post(url, body)
    .then((res) => {
      localStorage.setItem("jwt", res.data.accessToken);
      commit("USER_INFO", res.data);
      commit("LOGIN_STATUS", true);
    })
    .catch((err) => {
      console.log(err);
    });
}

export async function getUserInfo({ commit }, payload) {
  const memberId = payload.memberId;
  const jwt = payload.jwt;
  const url = `profiles/${memberId}`;
  await $axios
    .get(url, {
      headers: {
        Authorization: "Bearer " + jwt,
      },
    })
    .then((res) => {
      commit("USER_INFO", res.data);
    })
    .catch((err) => {
      console.log(err);
    });
}

export async function getOtherInfo({ commit }, payload) {
  const memberId = payload.memberId;
  const jwt = payload.jwt;
  const url = `profiles/${memberId}`;
  await $axios
    .get(url, {
      headers: {
        Authorization: "Bearer " + jwt,
      },
    })
    .then((res) => {
      commit("OTHER_INFO", res.data);
    })
    .catch((err) => {
      console.log(err);
    });
}

export async function profileChangeInfo({ state, dispatch }, payload) {
  const body = payload;
  const memberId = state.userInfo.memberId;
  const url = `profiles`;
  const header = localStorage.getItem("jwt");
  await $axios
    .put(url, body, {
      headers: {
        Authorization: "Bearer " + header,
      },
    })
    .then(() => {
      dispatch("getOtherInfo", {
        memberId: memberId,
        jwt: header,
      });
    })
    .catch((err) => {
      console.log(err);
    });
}

export async function profileChangeImage({ state }, payload) {
  let formData = new FormData();
  formData.append("profileImage", payload)
  const url = "profiles/profileImage";
  const header = localStorage.getItem("jwt");
  console.log(state)
  await $axios
    .post(url, formData, {
      headers: {
        Authorization: "Bearer " + header,
        "Content-Type": "multipart/form-data",
      },
    })
    .then((res) => {
      // dispatch("getOtherInfo", {
      //   memberId: memberId,
      //   jwt: header,
      // });
      console.log(res);
    })
    .catch((err) => {
      console.log(err);
    });
}

export async function getUserSchedule({ commit }) {
  const url = `profiles/calendar`
  const header = localStorage.getItem("jwt");
  await $axios
    .get(url, {
      headers: {
        Authorization: "Bearer " + header,
      },
    })
    .then((res) => {
      console.log(typeof(res.data.list[0].date[0]))
      commit("GET_USER_SCHEDULE", res.data.list)
    })
    .catch((err) => {
      console.log(err)
  })
}

export async function getDateTodo({ commit }, payload) {
  commit("ACTIVITY_PER_DAY", payload["activity"])
  commit("DATE_PER_DAY", payload["date"])
}

export async function mateArticleList({ commit }, payload) {
  const page = payload["page"]
  const size = payload["size"]
  const jwt = localStorage.getItem("jwt");
  const url = `mates?page=${page}&size=${size}`;
  await $axios
    .get(url, {
      headers: {
        Authorization: "Bearer " + jwt,
      },
    })
    .then((res) => {
      commit("MATE_ARTICLE_LIST", res.data.list);
    })
    .catch((err) => {
      console.log(err);
    });
}

export async function change({ commit }, payload) {
  await commit("CHANGE", payload)
}

export async function getPlaceSearchInfo({ commit }, searchFiltersData) {
  // console.log(searchFiltersData, "액션 데이터");
  const page = searchFiltersData.page;
  const body = searchFiltersData;
  const size = 20;
  const jwt = localStorage.getItem("jwt");
  const url = `places/search?page=${page}&size=${size}`;
  await $axios
    .post(url, body, {
      headers: {
        Authorization: "Bearer " + jwt,
      },
    })
    .then((res) => {
      console.log(res.data, "여기는 actions");
      commit("Place_Search_Info", res.data);
    })
    .catch((err) => {
      console.log(err);
    });
}

export async function selectSportsCategory({ commit }, categoryList) {
  commit("SELECT_SPORTS_CATEGORY", categoryList);
}
