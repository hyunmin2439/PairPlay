import store from "@/common/store";
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

export async function profileOtherInfo({ commit }, payload) {
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
  let formData = new FormData()
  formData.append("profileImage", payload.file)
  console.log(state);
  const url = "profiles/profileImage";
  const header = localStorage.getItem("jwt");
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

export async function profileUserSchedule({ commit }, payload) {
  const memberId = payload
  const url = `profiles/calendar/${memberId}`
  const header = localStorage.getItem("jwt");
  await $axios
    .get(url, {
      headers: {
        Authorization: "Bearer " + header,
      },
    })
    .then((res) => {
      console.log(res)
      commit("PROFILE_USER_SCHEDULE", res.data.list)
    })
    .catch((err) => {
      console.log(err);
    });
}

export async function profileDateTodo({ commit }, payload) {
  const url = `profiles/calendar/activity`;
  const jwt = localStorage.getItem("jwt");
  const body = payload;
  await $axios
    .post(url, body, {
      headers: {
        Authorization: "Bearer " + jwt,
      },
    })
    .then((res) => {
      console.log(res)
      commit("PROFILE_ACTIVITY_PER_DAY", res.data.calendarDetailActivityResList)
      commit("PROFILE_DATE_PER_DAY", body.date)
    })
    .catch((err) => {
      console.log(err);
    });
}

export async function profileMateListFrom({ commit }, payload) {
  const page = payload["page"]
  const size = payload["size"]
  const url = `profiles/mates/received?page=${page}&size=${size}`
  const jwt = localStorage.getItem("jwt");
  await $axios
    .get(url, {
      headers: {
        Authorization: "Bearer " + jwt,
      },
    })
    .then((res) => {
      commit("PROFILE_MATE_LIST_FROM", res.data);
    })
    .catch((err) => {
      console.log(err);
    });
}

export async function profileMateFromAccept({ dispatch }, payload) {
  const url = 'profiles/mates/accept'
  const body = { "id": payload['id'] }
  const page = payload['page']
  const jwt = localStorage.getItem("jwt")
  await $axios
    .put(url, body, {
      headers: {
        Authorization: "Bearer " + jwt
      }
    })
    .then((res) => {
      console.log(res)
      dispatch("profileMateListFrom", {
        'page': page, 'size': 3
      })
    })
    .catch((err) => {
      console.log(err)
  })
}

export async function profileMateFromReject({ dispatch }, payload) {
  const id = payload['id']
  const url = `profiles/mates/reject/${id}`
  const jwt = localStorage.getItem("jwt")
  await $axios
    .delete(url, {
      headers: {
        Authorization: "Bearer " + jwt
      }
    })
    .then((res) => {
      console.log(res)
      dispatch("profileMateListFrom", {
        'page': payload['page'], 'size': 3
      })
    })
    .catch((err) => {
      console.log(err)
  })
}

export async function profileMateListTo({ commit }, payload) {
  const page = payload["page"]
  const size = payload["size"]
  const url = `profiles/mates/send?page=${page}&size=${size}`
  const jwt = localStorage.getItem("jwt");
  await $axios
    .get(url, {
      headers: {
        Authorization: "Bearer " + jwt,
      },
    })
    .then((res) => {
      commit("PROFILE_MATE_LIST_TO", res.data);
    })
    .catch((err) => {
      console.log(err);
    });
}

export async function profileReservationList({ commit }, payload) {
  const page = payload
  const url = `profiles/places/${page}`
  const jwt = localStorage.getItem("jwt")
  await $axios
    .get(url, {
      headers: {
        Authorization: "Bearer " + jwt
      }
    })
    .then((res) => {
      commit("PROFILE_RESERVATION_LIST", res.data)
    })
    .catch((err) => {
      console.log(err)
  })
}

export async function profileReservationListMore({ commit }, payload) {
  const page = payload
  const url = `profiles/places/${page}`
  const jwt = localStorage.getItem("jwt")
  await $axios
    .get(url, {
      headers: {
        Authorization: "Bearer " + jwt
      }
    })
    .then((res) => {
      commit("PROFILE_RESERVATION_LIST_MORE", { 'dataList': res.data, 'page': page })
    })
    .catch((err) => {
      console.log(err)
  })
}

export async function profileReservationDoingList({ commit }, payload) {
  const page = payload
  const url = `profiles/places/reservation/${page}`
  const jwt = localStorage.getItem("jwt")
  await $axios
    .get(url, {
      headers: {
        Authorization: "Bearer " + jwt
      }
    })
    .then((res) => {
      commit("PROFILE_RESERVATION_LIST", res.data)
    })
    .catch((err) => {
      console.log(err)
  })
}

export async function profileReservationDoingListMore({ commit }, payload) {
  const page = payload
  const url = `profiles/places/reservation/${page}`
  const jwt = localStorage.getItem("jwt")
  await $axios
    .get(url, {
      headers: {
        Authorization: "Bearer " + jwt
      }
    })
    .then((res) => {
      commit("PROFILE_RESERVATION_LIST_MORE", { 'dataList': res.data, 'page': page })
    })
    .catch((err) => {
      console.log(err)
  })
}

export async function profileReservationDoneList({ commit }, payload) {
  const page = payload
  const url = `profiles/places/used/${page}`
  const jwt = localStorage.getItem("jwt")
  await $axios
    .get(url, {
      headers: {
        Authorization: "Bearer " + jwt
      }
    })
    .then((res) => {
      commit("PROFILE_RESERVATION_LIST", res.data)
    })
    .catch((err) => {
      console.log(err)
  })
}

export async function profileReservationDoneListMore({ commit }, payload) {
  const page = payload
  const url = `profiles/places/used/${page}`
  const jwt = localStorage.getItem("jwt")
  await $axios
    .get(url, {
      headers: {
        Authorization: "Bearer " + jwt
      }
    })
    .then((res) => {
      commit("PROFILE_RESERVATION_LIST_MORE", { 'dataList': res.data, 'page': page })
    })
    .catch((err) => {
      console.log(err)
  })
}

export async function profileCancelPlaceReservation({ dispatch }, payload) {
  const url = `places/reservation`
  const body = payload
  console.log(body)
  console.log(typeof(body["reservationId"]))
  const jwt = localStorage.getItem("jwt")
  await $axios
    .delete(url, {
      data: {
        'reservationId': body['reservationId']
      }
    }, {
      headers: {
        Authorization: "Bearer " + jwt
      }
    })
    .then((res) => {
      console.log(res)
      dispatch("profileReservationList")
    })
    .catch((err) => {
      console.log(err)
  })
}

export async function profileMateToCancle({ dispatch }, payload) {
  const id = payload['id']
  const url = `profiles/mates/cancel/${id}`
  const jwt = localStorage.getItem("jwt")
  await $axios
    .delete(url, {
      headers: {
        Authorization: "Bearer " + jwt
      }
    })
    .then(async (res) => {
      console.log(res)
      await dispatch("profileMateListTo", {
        'page': payload['page'], 'size': 3
      })
    })
    .catch((err) => {
      console.log(err)
  })
}

export async function profileLikePlace({ commit }, payload) {
  const page = payload
  const url = `profiles/places/like/${page}`
  const jwt = localStorage.getItem("jwt")
  await $axios
    .get(url, {
      headers: {
        Authorization: "Bearer " + jwt
      }
    })
    .then((res) => {
      console.log(res)
      commit("PROFILE_LIKE_PLACE", res.data)
    })
    .catch((err) => {
      console.log(err)
    })
}

export async function profileLikePlaceCancle({ dispatch }, payload) {
  const placeId = payload["id"]
  const page = payload["page"]
  const url = `places/like/${placeId}`
  const jwt = localStorage.getItem("jwt")
  await $axios
    .put(url, {}, {
      headers: {
        Authorization: "Bearer " + jwt
      }
    })
    .then((res) => {
      console.log(res)
      dispatch("profileLikePlace", page)
    })
    .catch((err) => {
      console.log(err)
  })
}

export async function profileLikeMate({ commit }, payload) {
  const page = payload
  const url = `profiles/mates/like?page=${page}`
  const jwt = localStorage.getItem("jwt")
  await $axios
    .get(url, {
      headers: {
        Authorization: "Bearer " + jwt
      }
    })
    .then((res) => {
      commit("PROFILE_LIKE_MATE", res.data)
    })
    .catch((err) => {
      console.log(err)
    })
}

export async function profileLikeMateCancle({ dispatch }, payload) {
  const activityId = payload["id"]
  const page = payload["page"]
  const url = `mates/like/${activityId}`
  const jwt = localStorage.getItem("jwt")
  await $axios
    .put(url, {}, {
      headers: {
        Authorization: "Bearer " + jwt
      }
    })
    .then((res) => {
      console.log(res)
      dispatch("profileLikeMate", page)
    })
    .catch((err) => {
      console.log(err)
  })
}

export async function mateArticleList({ commit }, payload) {
  const page = payload["page"];
  const jwt = localStorage.getItem("jwt");
  const url = `mates?page=${page}`;
  await $axios
    .get(url, {
      headers: {
        Authorization: "Bearer " + jwt,
      },
    })
    .then(async (res) => {
      // console.log(res.data.list, "있음?");
      if (res.data.list.length == 0) {
        alert("해당결과가 없습니다.");
      } else {
        console.log(res)
        commit("MATE_ARTICLE_FILTER", {
          "categoryId": 0,
          "gungu": "",
          "search": "",
          "sido": ""
        })
        await commit("MATE_ARTICLE_LIST", res.data.list);
      }
      // await commit("MATE_ARTICLE_LIST", res.data.list);
      // await commit("MATE_ARTICLE_PAGE", page);
    })
    .catch((err) => {
      console.log(err);
    });
}
export async function mateFilterChange({ commit }, payload) {
  const jwt = localStorage.getItem("jwt");
  const body = {
    "categoryId": payload.categoryId,
    "gungu": payload.gungu,
    "search": payload.search,
    "sido": payload.sido
  }
  const page = payload.page
  const url = `mates?page=${page}`;
  await $axios
    .post(url, body, {
      headers: {
        Authorization: "Bearer " + jwt,
      },
    })
    .then((res) => {
      console.log(res)
      commit("MATE_ARTICLE_FILTER", body)
      commit("MATE_ARTICLE_LIST", res.data.list);
    })
    .catch((err) => {
      console.log(err);
    });
}

export async function mateApplyFor({ state }, payload) {
  const body = payload
  const url = `mates/register`
  const jwt = localStorage.getItem("jwt");
  console.log(state)
  await $axios
    .post(url, body, {
      headers: {
        Authorization: "Bearer " + jwt,
      },
    })
    .then((res) => {
      console.log(res)
      alert("메이트 신청에 성공했습니다.")
    })
    .catch((err) => {
      console.log(err);
      alert("메이트 신청에 실패했습니다.")
    });
}

export async function mateLikeChange({ dispatch }, payload) {
  const body = {
    "categoryId": payload.categoryId,
    "gungu": payload.gungu,
    "search": payload.search,
    "sido": payload.sido,
    "page": payload.page,
  }
  const activityId = payload.activityId
  const url = `mates/like/${activityId}`
  const jwt = localStorage.getItem("jwt");
  $axios
    .put(url, {}, {
      headers: {
        Authorization: "Bearer " + jwt,
      },
    })
    .then((res) => {
      console.log(res)
      dispatch("mateFilterChange", body)
    })
    .catch((err) => {
      console.log(err);
    });
}

export async function getPlaceSearchInfo({ commit }, searchFiltersData) {
  const page = searchFiltersData.page;
  // console.log(page, "페이지");
  let body = searchFiltersData;
  // console.log(body, "바디");

  if (body.categoryList.length === 0) {
    // console.log("안되나");
    body.categoryList = [store.state.root.selectSportsCategory];
  } else {
    body = searchFiltersData;
  }
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
      if (res.data.placeList.length >= 1) {
        commit("PLACE_SEARCH_INFO", res.data);
        commit("CHANGE_POSITION", res.data);
      } else {
        // alert("데이터가없습니다.");
      }
    })
    .catch((err) => {
      console.log(err);
    });
}

export async function selectSportsCategory({ commit }, categoryList) {
  const selectSportsCategory = Object.values(categoryList)[0];
  await commit("SELECT_SPORTS_CATEGORY", selectSportsCategory);
}

export async function addPlaceFilters({ commit }, data) {
  await commit("ADD_PLACE_FILTERS", data);
  await store.dispatch("root/getPlaceSearchInfo", data);
}

export async function getPlaceDetailInfo({ commit }, id) {
  const jwt = localStorage.getItem("jwt");
  const url = `places/${id}`;
  await $axios
    .get(url, {
      headers: {
        Authorization: "Bearer " + jwt,
      },
    })
    .then((res) => {
      // console.log(res.data);
      commit("PLACE_DETAIL_INFO", res.data);
    })
    .catch((err) => {
      console.log(err);
    });
}
export async function getPlaceRecommend({ commit }) {
  const jwt = localStorage.getItem("jwt");
  const url = "places/popular";
  await $axios
    .get(url, {
      headers: {
        Authorization: "Bearer " + jwt,
      },
    })
    .then((res) => {
      // console.log(res.data.placeList);
      commit("PLACE_RECOMMEND", res.data.placeList.splice(0, 5));
    })
    .catch((err) => {
      console.log(err);
    });
}
export async function getPlaceRecent({ commit }) {
  const jwt = localStorage.getItem("jwt");
  const url = "places/recent";
  await $axios
    .get(url, {
      headers: {
        Authorization: "Bearer " + jwt,
      },
    })
    .then((res) => {
      // console.log(res.data.placeList);
      commit("PLACE_RECENT", res.data.placeList.splice(0, 5));
    })
    .catch((err) => {
      console.log(err);
    });
}

export async function checkReservation({ commit }, payload) {
  const jwt = localStorage.getItem("jwt");
  const url = "places/reservation/check";
  // let body = { placeId: payload.placeId, reservationDt: payload.reservationDt };
  const body = payload;
  // console.log(body, "굿바디");
  await $axios
    .post(url, body, {
      headers: {
        Authorization: "Bearer " + jwt,
      },
    })
    .then((res) => {
      // console.log(res, "나오나");
      commit("CHECK_RESERVATION", res.data.times);
    })
    .catch((err) => {
      console.log(err);
    });
}
