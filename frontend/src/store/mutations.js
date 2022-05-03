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

export function USER_INFO(state, data) {
  state.userInfo['address'] = data.address
  state.userInfo['birthDt'] = data.birthDt
  state.userInfo['description'] = data.description
  state.userInfo['email'] = data.email
  if (data.gender == 0) {
    state.userInfo['gender'] = '남성'
  } else {
    state.userInfo['gender'] = '여성'
  }
  state.userInfo['memberId'] = data.memberId
  state.userInfo['name'] = data.name
  state.userInfo['nickname'] = data.nickname
  state.userInfo['phone'] = data.phone
  state.userInfo['prfileImage'] = data.prfileImage
}