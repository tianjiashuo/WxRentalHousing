import config from './config'
import md5 from './md5'

//  http请求的类
export default class Http {
  // 头信息
  setHeader(){
    // 当前的时间戳
    let time = Date.now();
    // 头信息
    // ... 参数扩展
    return {
      username: config.username,
      password: config.password,
      timestemp: time,
      sign: md5(config.username + config.password + time + config.token)
    }
  }
  /**
   * 发起http请求，返回Promise对象
   */
  httpRequest({ url, data = {}, method = 'GET', header = {} }) {
    // 用户交互提交 显示 loading 提示框。
    wx.showLoading({
      title: '在跑了，不要着急...',
      mask: true
    });
    header = { ...this.setHeader(), ...header };
    // 请求
    return new Promise((resolve, reject) => {
      wx.request({
        url: config.domain + url,
        data,
        header,
        method,
        success: ret => resolve(ret),
        fail: err => reject(err),
        complete: () => {
          // 隐藏加载框
          wx.hideLoading();
        }
      });
    })
  }

  // 文件上传
  uploadFile({ url, filePath, formData = { node: 'renting' } }) {
    return new Promise((resolve, reject) => {
      //上传
      wx.uploadFile({
        url: config.domain + url,
        filePath,
        header: this.setHeader(),
        name: 'file',
        formData,
        success: ret => {
          resolve(ret)
        }
      });
    });
  }

}