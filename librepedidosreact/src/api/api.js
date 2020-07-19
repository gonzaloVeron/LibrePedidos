import axios from 'axios';

const server = 'http://localhost:7000';

const request = (type, path, body) => axios
  .request({ url: `${server}${path}`, method: type, data: body })
  .then(req => req.data);

export const sigIn = body => request('post', '/login', body);
export const register = body => request('post', '/register', body);
export const search = restName => request('get',`/search?q=${restName}`);
export const getUserWithOrders = userId => request('get', `/users/${userId}?include=orders`);
export const getUser = userId => request('get', `/users/${userId}`);
export const getRestaurant = restaurantId => request('get', `/restaurant/${restaurantId}`);
export const deliver = body => request('post', '/deliver', body);
export const voteOrder = orderId => body => request('put', `/deliver/${orderId}`, body);
