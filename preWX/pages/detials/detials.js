// pages/details/detials.js
Page({
 
  

  /**
   * 页面的初始数据
   */
  data: {
    id: 1,
    hostId:0,
    shortestLease:0,
    area: 0,
    price: 0,
    floor: 0,
    address: "",
    title: "",
    type: "",
    orientation: "",
    furniture: "",
    isElevator: false,
    isPet: false,
    isForm: false,
    isState: false
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    let that = this;
    that.setData({
      houseId:options.houseId
    })
    wx.request({
      url: 'http://47.94.170.167:8080/rentAllInfo/2',
      method:'GET',
      header: {
        'Content-Type': 'application/json'
      },
      success: function (res) {
        console.log(res.data)
        that.setData({
         title:res.data.rentInfo.title,
         address:res.data.rentInfo.address,
         type:res.data.rentInfo.type,
         orientation:res.data.rentInfo.orientation,
         furniture:res.data.rentInfo.furniture,
         isElevator:res.data.rentInfo.isElevator,
         shortestLease:res.data.rentInfo.shortestLease,
         images:res.data.imageList

        })
      }
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