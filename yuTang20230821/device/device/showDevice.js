// pages/index/device/showDevice.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    video: null,
    openSound: true,
    videoLoadingStatus: 0,
    videoNetWorkError: false,
    isFullScreen: false,
    screenOrientation: '',
    isLandScape: false,
    isfullscreenBtn: true,
    fullscreen: false,
    videoContext: null,
    autoPlay: false,
    autoRo: false,
    player: null,
    playerNum: 2,
    videoList: [],
    videosUrl:[], // 链接地址 
    videoSerialNo:[],
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function () {
    this.load()

    
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady() {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow() {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide() {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload() {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh() {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom() {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage() {

  },
  // 加载视频链接
  load(){
    wx.request({
      url: 'http://localhost:9090/live-url',
      method: 'GET',
      success:(res) =>{
        this.setData({
          videoList: res.data,
          videosUrl: res.data.map(iterm => iterm.hlsUrl),
          videoSerialNo: res.data.map(iterm => iterm.serialNumber)
        })
      }
    })
  }
  
})