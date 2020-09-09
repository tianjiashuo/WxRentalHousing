import Http from '../utils/Http';
import url from '../utils/url';
import Cache from '../utils/Cache';

class Model extends Http {
    // 通知列表
    getList() {
        return this.httpRequest({
            url: url.notices,
            data: { renting_id: Cache.get('id') }
        });
    }
}

export default new Model;