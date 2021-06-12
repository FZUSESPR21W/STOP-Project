<template>
  <div>
    <!-- 公告内容（富文本） -->
    <quillEditor ref="QuillEditor" class="editor" v-model="text" :options="editorOption" @input="$emit(mode + 'Change', text)"/>
    <!-- element-ui 上传 -->
    <el-upload action="#" class="avatar-uploader" name="img" :show-file-list="false" :http-request="uploadSectionFile"/>
  </div>
</template>

<script>
import 'quill/dist/quill.core.css'
import 'quill/dist/quill.snow.css'
import 'quill/dist/quill.bubble.css'

import { quillEditor } from 'vue-quill-editor'
import toolbarOptions from './richTextEditor.config'

export default {
  name: "richTextEditor",
  props: ['fatherValue','mode'],
  watch: {
    fatherValue(val) {
      this.text = val
    }
  },
  components: {
    quillEditor
  },
  data() {
    return {
      editorOption:  {
        scrollingContainer: '#editorcontainer',
        placeholder: '',
        theme: 'snow',
        modules: {
          toolbar: {
            // 工具栏
            container: toolbarOptions,
            handlers: {
              'image': function (value) {
                if (value) {
                  // upload点击上传事件
                  document.querySelector('.avatar-uploader input').click()
                } else {
                  this.quill.format('image', false)
                }
              }
            }
          }
        }
      },
      text: ''
    }
  },
  mounted() {

  },
  methods: {
    // 上传操作
    uploadSectionFile(param) {
      var fileObj = param.file
      // FormData 对象
      var form = new FormData()
      // 文件对象
      form.append('file', fileObj)
      this.$api.Notice.upLoadImg(form).then(res => {
        this.uploadSuccess(res)
      })
    },
    // 上传成功操作
    uploadSuccess(res) {
      // 获取富文本组件实例
      let quill = this.$refs.QuillEditor.quill
      // 如果上传成功
      if (res) {
        // 获取光标所在位置
        let length = quill.getSelection().index;
        // 插入图片，res为服务器返回的图片链接地址
        quill.insertEmbed(length, 'image', `https://api.shawnxixi.icu${res.data.data.pictureUrl}`)
        // 调整光标到最后
        quill.setSelection(length + 1)
      } else {
        // 提示信息，需引入Message
        this.$message.error('图片插入失败')
      }
    }
  }
}
</script>

<style scoped>

</style>