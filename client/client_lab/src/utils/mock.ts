// @ts-ignore
import mock from 'mockjs';

export function initMock() {
    mock.mock('/api/shop-data/find_open_shop', ()=>{
        return mock.mock({
            code: '200',
            "data|1-10":[{
                'date': '@date',
                'shopId': '@integer(1-1000)',
                'shopName': '@string(5)'
            }]
        })
    });
    mock.mock('/api/product-data/find_all_sell_product_data', ()=>{
        return mock.mock({
            code: '200',
            "data|1-10":[{
                'date': '@date',
                'shopId': '@integer(1-1000)',
                'productId': '@integer(1-10000)',
                'productName': '@string(3-8)'
            }]
        })
    });
    mock.mock('/api/user-data/login', ()=>{
        return mock.mock({
            code: '200',
            "data":{
                'date': '@date',
                'id': '@integer(1-1000)',
                'userName': '@string(5)',
                'userType': 'sys_user'
            }
        })
    });
}
