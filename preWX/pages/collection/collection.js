// pages/renthouse/renthouse.js
Page({
  
  /**
   * 页面的初始数据
   */
  data: {
    collectionNum:0,
    current:0,
    collections:[],
    showDialog:true
  },
 
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this;
    var openId=wx.getStorageSync('openId');
    if(typeof(openId) != 'undefined'){
      wx.request({
        url:'http://47.94.170.167:8080/getCollection/'+openId,
        header: { "content-type": "application/json" },
        method: 'POST',
        dataType: 'json',
        responseType: 'text',
        success: function (res) {
          console.log("collections"+res.data)
          that.setData({
            collections: res.data,
          })
        },
        fail: function (res) {
          console.log('fail')
        },
      })
    } 
  },
  goNewsDetail:function(event)
  {
   wx.navigateTo({
     url: '/pages/detials/detials?id=' + event.currentTarget.dataset.newsid
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
    var that = this;
    that.setData({
      keywords:"",
      isChecked:false
    })
    console.log(that.is_pet);
    wx.request({
      url: 'http://47.94.170.167:8080/rent/all',
    //  url: 'http://localhost:8080/rent/all',
      data: '',
      header: { "content-type": "application/json" },
      method: 'POST',
      dataType: 'json',
      responseType: 'text',
      success: function (res) {
        console.log("allRent"+res.data);
        that.setData({
          allRent: res.data,
          current:0
        })
        wx.stopPullDownRefresh();
      },
      fail: function (res) {
        console.log('fail '+res)
      },
    })
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
  ,

  noTouchMove: function() {
  },
  
})