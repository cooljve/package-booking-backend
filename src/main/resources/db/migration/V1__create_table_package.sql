create table `package` (
  `order_id` int primary key auto_increment,
  `order_number` varchar ,
  `receiver` varchar ,
  `phone` varchar ,
  `status` varchar ,
  `book_date` timestamp ,
  unique key (`order_number`)
);