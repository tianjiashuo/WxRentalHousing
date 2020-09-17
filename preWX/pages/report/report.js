// pages/report/report.js
Page({



  data: {
    title: '',
    content: ''
  },
  inputTitle: function (e) {
    this.setData({
      title: e.detail.value
    })
  },
  inputContent: function (e) {
    this.setData({
      content: e.detail.value
    })
  },
  save: function (e) {
    //var title = this.data.title;
    var content = this.data.content;
    //console.log(content)
    // 提交请求
    wx.showToast({
      title: '提交成功',
      duration: 1000
    })
    wx.setStorage({
      key: 'report',
      data: content,
    })
    // 返回上一页
  },
  
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

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

  }
})