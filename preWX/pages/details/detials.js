// pages/details/detials.js
Page({
  houseInfor(e) {
    wx.navigateTo({
      url: '../exploration/exploration',
    })
  },
  /**
   * 页面的初始数据
   */
  data: {},
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