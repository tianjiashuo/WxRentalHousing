// pages/renthouse/renthouse.js
Page({
  
  /**
   * 页面的初始数据
   */
  data: {
    keywords:"",
    current:0,
    swiper:[],
    type:[{id: 0,type: "全部", typeid:0},
          {id: 1,type: "待整租", typeid:1},
          {id: 2,type: "待合租", typeid:2}],
    allRent:[],
    keywords:"",
    isChecked:false
  },
 
  click(e) {
    this.setData({
      current: e.currentTarget.dataset.set
    })
  },
  doSearch:function(e){
    console.log("form 发生了 submit",e.detail.value)
    let that = this;
    wx.request({
     // url: 'http://47.94.170.167:8080/rent/select',
     url: 'http://localhost:8080/rent/select', 
     data:{
        "key":e.detail.value.keywords,
        "is_pet":e.detail.value.is_pet,
        "is_elevator":e.detail.value.is_elevator
      },
      header: { "content-type": "application/json"},
      method: 'POST',
      dataType: 'json',
      responseType: 'text',
      success: function(res) {
        console.log("search allRent"+res.data)
        that.setData({
          allRent: res.data,
          current:0
        })
        
      },
      fail: function(res) {
        console.log('fail '+res)
      },
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

    var that = this;
    wx.request({
     // url: 'http://47.94.170.167:8080/page/swiper',
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
      }
    })
   
    wx.request({
     //url: 'http://47.94.170.167:8080/rent/all',
     url: 'http://localhost:8080/rent/all', 
     data: '',
      header: { "content-type": "application/json" },
      method: 'GET',
      dataType: 'json',
      responseType: 'text',
      success: function (res) {
        console.log("allRent"+res.data)
        that.setData({
          allRent: res.data,
          id:res.data.id
        })
      },
      fail: function (res) {
        console.log('fail')
      },
    })
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
      method: 'GET',
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
})