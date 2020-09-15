// pages/details/detials.js
Page({
 
  /**
   * 页面的初始数据
   */
  data: {
    address:""
  },
  houseInfor(e) {
    wx.navigateTo({
      url: '../exploration/exploration',
    })
  },
  Tarvel(e) {
    let sTop = this.data.scrollTop;
    console.log(sTop);
    this.setData({
      display: 'block',
      hidden: 'hidden'
    });
    let displayData = this.data.data6.travel_infor;
    this.setData({
      datas: displayData
    })
  },
  Cancel(e) {
    let sTop = this.data.scrollTop;
    console.log(sTop);
    this.setData({
      display: 'block',
      hidden: 'hidden'
    });
    let displayData = this.data.data7.cancel;
    this.setData({
      datas: displayData
    })
  },
  Tip(e) {
    let sTop = this.data.scrollTop;
    console.log(sTop);
    this.setData({
      display: 'block',
      hidden: 'hidden'
    });
    let displayData = this.data.data7.tip;
    this.setData({
      datas: displayData
    })
  },
  Address(e) {
    let sTop = this.data.scrollTop;
    console.log(sTop);
    this.setData({
      display: 'block',
      hidden: 'hidden'
    });
    let displayData = this.data.data6.address_infor;
    this.setData({
      datas: displayData
    })
  },
  click(e) {
    console.log(e);
    if (e.touches[0].pageY < 420) {
      this.setData({
        display: 'none',
        hidden: ''
      });
    }
  },

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
    wx.request({
      url: 'http://47.94.170.167:8080/rentAllInfo/1',
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
         shortestLease:res.data.rentInfo.shortestLease

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