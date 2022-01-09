# Kalman Desktop

A tool to implement and understand [Kalman Filters](https://en.wikipedia.org/wiki/Kalman_filter).

Kalman Desktop along with [Kalman Mobile]() is a Server-Client pair that reads the accelerometer readings from an Android device and sends them over to a desktop application over websockets.
It then moves the cursor on the screen according to the change in acceleration of the mobile device.


A `DataObject` is recieved which contains acceleration along `x`, `y` and `z` in m/s<sup>2</sup> and a [`timestamp`](https://developer.android.com/reference/android/hardware/SensorEvent#timestamp) in nanoseconds (time since the device has been on).

### Note
Kalman mobile reads [`Sensor.TYPE_ACCELEROMETER`](https://developer.android.com/reference/android/hardware/SensorEvent#sensor.type_accelerometer:) and **not** [`Sensor.TYPE_ACCELEROMETER_UNCALIBRATED`](https://developer.android.com/reference/android/hardware/SensorEvent#sensor.type_accelerometer_uncalibrated:). Meaning it is bias compensated. 

## Moving the cursor
```java
Robot robot = new Robot();
Point2D p = robot.getMousePosition();
robot.mouseMove(p.getX() + acc_x, p.getY() + acc_y);
```

## Websocket Compatibility
| Server  | Client |
| ------- | ------ |
| [Netty-socketio](https://github.com/mrniko/netty-socketio) v1.7.19  | [Socket.IO-client Java](https://github.com/socketio/socket.io-client-java) v1.0.1 |

## References for Kalman filter
* [Kalmanfilter.net](https://www.kalmanfilter.net/)
* [Kalman's seminal paper (1960)](http://www.cs.unc.edu/~welch/kalman/media/pdf/Kalman1960.pdf)
* [UNC's collection of resources](https://www.cs.unc.edu/~welch/kalman/index.html)

## License
GNU General Public License

#
_App icon stolen from kalmanfilter.net_
