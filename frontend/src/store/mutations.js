// import state from "./state";

export function SIGNUP_DUPLICATE_EMAIL(state, data) {
  state.emailDuplicate = data;
}

export function SIGNUP_DUPLICATE_NICKNAME(state, data) {
  state.nicknameDuplicate = data;
}

export function LOGIN_STATUS(state, data) {
  state.loginStatus = data;
}

export function USER_INFO(state, data) {
  state.userInfo["address"] = data.address;
  state.userInfo["birthDt"] = data.birthDt;
  state.userInfo["description"] = data.description;
  state.userInfo["email"] = data.email;
  if (data.gender == 0) {
    state.userInfo["gender"] = "남성";
  } else {
    state.userInfo["gender"] = "여성";
  }
  state.userInfo["memberId"] = data.memberId;
  state.userInfo["name"] = data.name;
  state.userInfo["nickname"] = data.nickname;
  state.userInfo["phone"] = data.phone;
  state.userInfo["profileImage"] = data.profileImage;
}

export function OTHER_INFO(state, data) {
  state.otherInfo = [];
  state.otherInfo["address"] = data.address;
  state.otherInfo["birthDt"] = String(data.birthDt[0]) + "년 " + String(data.birthDt[1]) + "월 " + String(data.birthDt[2]) + "일";
  state.otherInfo["description"] = data.description;
  state.otherInfo["email"] = data.email;
  if (data.gender == 0) {
    state.otherInfo["gender"] = "남성";
  } else {
    state.otherInfo["gender"] = "여성";
  }
  state.otherInfo["memberId"] = data.memberId;
  state.otherInfo["name"] = data.name;
  state.otherInfo["nickname"] = data.nickname;
  state.otherInfo["phone"] = data.phone;
  state.otherInfo["profileImage"] = data.profileImage;
}

export function PROFILE_USER_SCHEDULE(state, data) {
  state.profileUserSchedule = [];
  for (var i in data) {
    var sub = {};
    sub["date"] = String(data[i].date[0]) + "-" + String(data[i].date[1]) + "-" + String(data[i].date[2]);
    sub["count"] = data[i].count;
    state.profileUserSchedule.push(sub);
  }
}

export function PROFILE_ACTIVITY_PER_DAY(state, data) {
  state.profileActivityPerDay = data;
}

export function PROFILE_DATE_PER_DAY(state, data) {
  state.profileDatePerDay = data;
}

export function PROFILE_MATE_LIST_FROM(state, data) {
  state.profileMateListFromTotalPage = 0;
  state.profileMateListFromTotalPage = data.totalPages;
  state.profileMateListFrom = [];
  state.profileMateListFrom = data.list;
}

export function PROFILE_MATE_LIST_TO(state, data) {
  state.profileMateListToTotalPage = 0;
  state.profileMateListToTotalPage = data.totalPages;
  state.profileMateListTo = [];
  state.profileMateListTo = data.list;
}

export function PROFILE_RESERVATION_LIST(state, data) {
  state.profileReservationList = [];
  state.profileReservationList = data.list;
}

export function PROFILE_RESERVATION_LIST_MORE(state, data) {
  console.log(data.dataList);
  const dataList = data.dataList;
  var page = data.page;
  var totalPages = dataList.totalPages;
  var totalElements = dataList.totalElements;
  if (page < totalPages) {
    for (var i = 0; i < 10; i++) {
      state.profileReservationList.push(dataList.list[i]);
    }
  } else {
    for (var j = totalPages * 10; j < totalElements; j++) {
      state.profileReservationList.push(dataList.list[j - 10 * totalPages]);
    }
  }
}

export function PROFILE_LIKE_PLACE(state, data) {
  state.profileLikePlaceTotalPages = -1;
  state.profileLikePlaceTotalPages = data.totalPages;
  state.profileLikePlaceList = [];
  state.profileLikePlaceList = data.list;
}

export function PROFILE_LIKE_MATE(state, data) {
  state.profileLikeMateTotalPages = -1;
  state.profileLikeMateTotalPages = data.totalPages;
  state.profileLikeMateList = [];
  state.profileLikeMateList = data.list;
}

export async function MATE_ARTICLE_LIST(state, data) {
  state.mateArticleListTotalElements = 0;
  state.mateArticleListTotalElements = data.totalElements;
  state.mateArticleListTotalPage = 0;
  state.mateArticleListTotalPage = data.totalPages;
  state.mateArticleList = [];
  for (var i in data.content) {
    var sub = {};
    sub["activityId"] = data.content[i]["activityId"];
    sub["categoryId"] = data.content[i]["categoryId"];
    sub["description"] = data.content[i]["description"];
    sub["location"] = data.content[i]["location"];
    sub["title"] = data.content[i]["title"];
    sub["like"] = data.content[i]["like"];
    var startDate = new Date(data.content[i]["createdDate"][0], data.content[i]["createdDate"][1] - 1, data.content[i]["createdDate"][2]);
    var today = new Date();
    var diff = today.getTime() - startDate.getTime();
    sub["createdDate"] = String(data.content[i]["createdDate"][0]) + "-" + String(data.content[i]["createdDate"][1]) + "-" + String(data.content[i]["createdDate"][2]);
    sub["timeDiff"] = Math.floor(diff / (1000 * 60 * 60 * 24));
    // console.log(new Date(k.getTimezoneOffset() * 60000).toISOString())
    // String(data[i].date[0]) + '-' + String(data[i].date[1]) + '-' + String(data[i].date[2])
    state.mateArticleList.push(sub);
  }
}

export async function MATE_ARTICLE_FILTER(state, data) {
  state.mateArticleFilter = {};
  state.mateArticleFilter = data;
}

export async function PLACE_SEARCH_INFO(state, data) {
  state.placeSearchInfo = "";
  state.placeSearchInfo = data;
}

export async function SELECT_SPORTS_CATEGORY(state, data) {
  state.selectSportsCategory = "";
  state.selectSportsCategory = data;
}

export async function SUBMIT_LIKE(state, data) {
  state.placeLike = data;
}
export async function CHANGE(state, data) {
  state.changeList = {};
  state.changeList = data;
}
export async function ADD_PLACE_FILTERS(state, data) {
  state.addPlaceFilters = {};
  state.addPlaceFilters = data;
}

export async function PLACE_DETAIL_INFO(state, data) {
  state.placeDetailInfo = data.placeDetail;
}
export async function CHANGE_POSITION(state, data) {
  state.mapPosition = [];
  let longitude;
  let latitude;
  let placeName;
  // console.log(data);
  const markers = [];
  for (let i = 0; i < data.placeList.length; i++) {
    longitude = data.placeList[i].longitude;
    latitude = data.placeList[i].latitude;
    placeName = data.placeList[i].name;
    // console.log(tempLat);
    let tempList = { longitude, latitude, placeName };
    markers.push(tempList);
  }
  console.log(markers, "마커임");
  // console.log(markers, "템프리스트");
  state.mapPosition = markers;
}
export async function PLACE_RECOMMEND(state, data) {
  state.placeRecommend = data;
}
export async function PLACE_RECENT(state, data) {
  state.placeRecent = data;
}
export async function CHECK_RESERVATION(state, data) {
  state.reservationCheck = data;
}
export async function SHOW_MAP_MARKER(state, data) {
  state.showMapMarker = data;
}
