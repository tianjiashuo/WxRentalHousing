class Cache {

  // 过期时间，单位为 秒
  set(key, data, expire = 3600) {
    // 过期时间 getTime()返回是毫秒
    expire = new Date().getTime() + expire * 1000;
    // 写入到缓存中的数据
    let value = { expire, data }
    wx.setStorageSync(key, value);
    return true;
  }

  // 判断key是否存在
  // key真的不存在，key存在但已经过期了
  has(key) {
    // 获取获取中的数据
    let data = wx.getStorageSync(key);
    if (!data) {
      return false;
    }
    // 获取当前时间
    let ctime = new Date().getTime();
    let expire = data.expire;
    if (ctime < expire) {
      return true;
    }
    // 过期了,删除此key
    wx.removeStorageSync(key);
    return false;
  }

  // 获取
  get(key) {
    if (!this.has(key)) {
      return '';
    }
    return wx.getStorageSync(key).data
  }

  // 永久有效
  forever(key,value){
    return this.set(key, value, 999999999999);
  }

  // 删除
  delete(key){
    wx.removeStorageSync(key);
    return true;
  }

}

export default new Cache;