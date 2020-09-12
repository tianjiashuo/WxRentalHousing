// pages/renthouse/renthouse.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
   
  },
  

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    // wx.login({
    //   success: res => {
    //     // 发送 res.code 到后台换取 openId, sessionKey, unionId
    //     if (res.code) {
    //       wx.request({
    //         url: 'http://localhost:8080/user/login',
    //         method: 'POST',
    //         data: {
    //           code: res.code
    //         },
    //         header: {
    //           'content-type': 'application/x-www-form-urlencoded'
    //         },
    //         success(res) {
    //           console.log("openid:"+res.data.openid);
    //           if (res.data.openid != "" || res.data.openid!=null){
    //             // 登录成功
    //             // wx.setStorageSync("openid", res.data.openid);//将用户id保存到缓存中
    //             // wx.setStorageSync("session_key", res.data.session_key);//将session_key保存到缓存中
    //             console.log("openid", res.data.openid);
    //             console.log("session_key",res.data.session_key);
    //           }else{
    //             // 登录失败
    //             // TODO 跳转到错误页面，要求用户重试
    //             return false;
    //           }
    //         }
    //       })
    //     } else {
    //       console.log('获取用户登录态失败！' + res.errMsg)
    //     }
    //   }
    // })
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  },
  getPhoneNumber: function(e) { 
    console.log(e.detail.errMsg) 
    console.log(e.detail.iv) 
    console.log(e.detail.encryptedData) 
  } 
})