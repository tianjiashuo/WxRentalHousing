// pages/details/detials.js
const app = getApp();
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
    isState: false,
    isCollect:null,
   
  },


  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    let that = this;
    //console.log(options.id);
    var hId = options.id
    wx.request({
      url: 'http://47.94.170.167:8080/getHouseRoommates/'+hId,
      method:"GET",
      header: {
        'Content-Type': 'application/json'
      },
      success:function(res){
        console.log(res.data)
        that.setData({
          allroommates: res.data.roommates,
          id:res.data.id,
          gender:res.data.roommates.gender?"man":"woman",
        })
      },
      fail:function(){
        console.log("house failed")
      }
    })
    wx.setStorage({
      data: hId,
      key: 'renthouseId',
    })
    wx.request({
      url: 'http://47.94.170.167:8080/rentAllInfo/'+options.id,
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
         images:res.data.imageList,
         hostId:res.data.rentInfo.hostId,
         price:res.data.rentInfo.price,
         data:res.data.rentInfo,
         isForm:res.data.rentInfo.isForm
        })
        // wx.setStorage({
        //   key:"1",
        //   data:res.data.rentInfo.id
        // })
        wx.request({
          url: 'http://47.94.170.167:8080/userInfo/'+res.data.rentInfo.hostId,
          method:'GET',
          header: {
            'Content-Type': 'application/json'
          },
          success:function(res){
            console.log(res.data)
            that.setData({
              head:res.data.head,
              nickname:res.data.nickname,
              introduction:res.data.introduction,
              phone:res.data.phone,
              gender:res.data.gender
            })
          }
        })
      }
    })
    wx.request({
      
      url: 'http://47.94.170.167:8080/uerInfo/'+hostId,
      method:'GET',
      header: {
        'Content-Type': 'application/json'
      },
    })

    // wx.request({
    //   url: 'http://localhost:8080/roommatesinfo/'+hId,
    //   method:"GET",
    //   header: {
    //     'Content-Type': 'application/json'
    //   },
    //   success:function(res){
    //     console.log(res.data+"house")
    //   },
    //   fail:function(){
    //     console.log("house failed")
    //   }
    // })

  },
  toCollect () {
    var bol = this.data.isCollect; // 获取状态
    this.setData({
    isCollect:!bol // 改变状态
    })
    wx.getStorage({
      key: "renthouseId",
      success: function(res) {
          console.log(res.data)
          console.log(!bol)
          // this.houseId = res.data
          // console.log(this.houseId)
          wx.setStorage({
            data: !bol,
            key: '2',
          })
          if(!bol == true){
            wx.request({
              url: 'http://47.94.170.167:8080/addCollection',
              data:{
                "user_id":1,
                "house_id":res.data,
                "house_type":0
              },
              method:"POST",
              header: {
                'Content-Type': 'application/json'
              },
              success:function(res){
                console.log(res.data)
              }
            })
          }
          else{
            wx.getStorage({
              key: 'collectionid',
              success:function(res){
                //console.log(res.data)
                wx.request({
                  url: 'http://47.94.170.167:8080/cancelCollection/'+res.data,
                  method:"DELETE",
                  header: {
                    'Content-Type': 'application/json'
                  },
                  success:function(res){
                    console.log("删除成功"+res.data)
                  }
                })
              }
            })
          }
      }
    })},

    goNewsDetail:function(event)
    {
     wx.navigateTo({
       url: '/pages/report/report'
     })
    },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
  },

  save:function(){
    if(app.globalData.hasUserInfo){
    wx.getStorage({
      key: 'renthouseId',
      success:function(res){
        console.log("rentHOUSEId===========",res.data)
        var houseId = res.data
        wx.request({
          url: 'http://47.94.170.167:8080/addApplocation',
          data:{
            "houseId":houseId,
            "userId":wx.getStorageSync('openId')
          },
          method:"POST",
          header: {
            'Content-Type': 'application/json'
          },
          success:function(res){
            console.log("app"+res.data);
            wx.showToast({
              title: '申请成功',
            })
          }
        })
      }
    })}else{
       wx.showToast({
         icon:"none",
         title: '请先登陆',
       })
    }
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    var that = this;
    wx.getStorage({
      key: 'renthouseId',
      success:function(res){
        var houseId = res.data
        wx.getStorage({
          key: 'report',
          success:function(res){
            var content = res.data
            wx.request({
              url: 'http://47.94.170.167:8080/addReport',
              data:{
                "user_id":wx.getStorageSync('openId'),
                "house_id":houseId,
                "content":content,
                "house_type":0
              },
              method:"POST",
              header: {
                'Content-Type': 'application/json'
              },
              success:function(res){
                console.log("haha"+res.data)
              },
              fail:function(){
                console.log("failed")
              }
            })
          }
        })
      }
    })
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