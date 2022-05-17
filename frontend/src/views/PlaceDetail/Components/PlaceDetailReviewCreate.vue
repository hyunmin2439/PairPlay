<template>
  <div class="button-total-frame">
    <button type="button" class="btn btn-outline-dark" data-bs-toggle="modal" data-bs-target="#exampleModal" data-bs-whatever="@mdo">글쓰기</button>
    <div class="modal" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
      <!-- <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true"> -->
      <div class="modal-dialog modal-xl">
        <div class="modal-content">
          <!-- <div class="modal-header"> -->
          <!-- <h5 class="modal-title" id="exampleModalLabel">리뷰 작성</h5> -->
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          <!-- </div> -->
          <div class="modal-body">
            <div class="container-fluid">
              <div class="row d-flex">
                <div class="col-md-6 ms-auto">.col-md-6 .ms-auto</div>
                <div class="col-md-6 ms-auto">
                  <!-- 업로드 시작 -->
                  <div class="FeedCreate-contentbox d-flex">
                    <div class="FeedCreate-leftbox d-flex justify-content-center align-items-center">
                      <div v-if="feedCreateImageList.ImageList.length > 0" class="FeedCreate-leftbox-contentBox-Frame d-flex justify-content-center align-items-center">
                        <img :src="`${feedCreateImageList.ImageList[0]}`" alt="" />
                        <!-- <div class="FeedCreate-contentbox-UploadImgFrame"><feed-create-carousel class="feed-create-carousel" :ImageList="feedCreateImageList"></feed-create-carousel></div> -->
                      </div>
                    </div>
                    <div v-if="feedCreateImageList.ImageList.length == 0">
                      <div>
                        <!-- <i @click="uploadImg" class="bi bi-images"></i> -->
                        <label for="fileName">
                          <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-images" viewBox="0 0 16 16">
                            <path d="M4.502 9a1.5 1.5 0 1 0 0-3 1.5 1.5 0 0 0 0 3z" />
                            <path
                              d="M14.002 13a2 2 0 0 1-2 2h-10a2 2 0 0 1-2-2V5A2 2 0 0 1 2 3a2 2 0 0 1 2-2h10a2 2 0 0 1 2 2v8a2 2 0 0 1-1.998 2zM14 2H4a1 1 0 0 0-1 1h9.002a2 2 0 0 1 2 2v7A1 1 0 0 0 15 11V3a1 1 0 0 0-1-1zM2.002 4a1 1 0 0 0-1 1v8l2.646-2.354a.5.5 0 0 1 .63-.062l2.66 1.773 3.71-3.71a.5.5 0 0 1 .577-.094l1.777 1.947V5a1 1 0 0 0-1-1h-10z"
                            />
                          </svg>
                        </label>
                        <form align="left" method="post" enctype="multipart/form-data" class="d-flex flex-column align-items-center">
                          <input ref="image" type="file" multiple="multiple" id="fileName" accept="image/*" @change="uploadImg" style="display: none" />
                          <p>클릭하여 사진을 첨부해주세요.</p>
                          <!-- <i class="bi bi-images"></i> -->
                        </form>
                      </div>
                    </div>
                  </div>

                  <!-- 업로드 끝 -->
                </div>
              </div>
            </div>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
            <button type="button" class="btn btn-primary">Send message</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { reactive } from "vue";
// import { useStore } from 'vuex'
export default {
  name: "PlaceDetailReviewCreate",
  setup() {
    const feedCreateImageList = {
      ImageList: [],
    };
    const form = reactive({
      file: null,
    });
    const selectFile = (data) => {
      form.file = data.target.files[0];
    };
    return { feedCreateImageList, form, selectFile };
  },
};
</script>

<style lang="scss" scoped>
* {
  // color: black;
  font-size: 13px;
}
.button-total-frame {
  padding: 0 0 0 0;
}
.btn.btn-dark {
  width: 80px;
  height: 40px;
}
</style>
