// pages/renthouse/renthouse.js
Page({
  houseInfor(e) {
    wx.navigateTo({
      url: '../exploration/exploration',
    })
  },
  /**
   * 页面的初始数据
   */
  data: {
    keywords:"",
    current:0,
    swiper:[],
    city:[{id: 0,type: "全部", typeid:0},
          {id: 1,type: "待整租", typeid:1},
          {id: 2,type: "待合租", typeid:2}],
    typehouse:[],
    twocurrent:0,
    hotplace:[],
    hotplacehouse:[]

  },
 
  click(e) {
    var id = e.currentTarget.dataset.set;
    this.setData({
      current: id
    })
    var that = this;
    // wx.request({
    //   url: 'http://localhost:8888/cityhouse',
    //   data: { id: id},
    //   header: { "content-type": "application/json" },
    //   method: 'POST',
    //   dataType: 'json',
    //   responseType: 'text',
    //   success: function (res) {
    //     console.log(res.data)
    //     that.setData({
    //       cityhouse: res.data
    //     })
    //   },
    //   fail: function (res) {
    //     console.log('fail')
    //   },
    // })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this;
    wx.request({
      url: 'http://localhost:8080/page/swiper',
      data: '',
      header: { "content-type": "application/json"},
      method: 'GET',
      dataType: 'json',
      responseType: 'text',
      success: function(res) {
        console.log("swiper"+res.data)
        that.setData({
          swiper:res.data
        })
      },
      fail: function(res) {
        console.log('fail')
      },
    })
   
    wx.request({
      url: 'http://localhost:8888/hotplace',
      data: '',
      header: { "content-type": "application/json" },
      method: 'POST',
      dataType: 'json',
      responseType: 'text',
      success: function (res) {
        console.log(res.data)
        that.setData({
          hotplace: res.data
        })
      },
      fail: function (res) {
        console.log('fail')
      },
    })

    wx.request({
      url: 'http://localhost:8888/cityhouse',
      data: { id: 1 },
      header: { "content-type": "application/json" },
      method: 'POST',
      dataType: 'json',
      responseType: 'text',
      success: function (res) {
        console.log(res.data)
        that.setData({
          cityhouse: res.data
        })
      },
      fail: function (res) {
        console.log('fail')
      },
    })
    wx.request({
      url: 'http://localhost:8888/cityhouse',
      data: { id: 1 },
      header: { "content-type": "application/json" },
      method: 'POST',
      dataType: 'json',
      responseType: 'text',
      success: function (res) {
        console.log(res.data)
        that.setData({
          hotplacehouse: res.data
        })
      },
      fail: function (res) {
        console.log('fail')
      },
    })

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