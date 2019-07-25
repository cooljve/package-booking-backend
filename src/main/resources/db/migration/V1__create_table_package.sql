create table `package` (
  `order_id` int primary key auto_increment,
  `order_number` varchar not null ,
  `receiver` varchar not null ,
  `phone` varchar not null ,
  `status` varchar not null ,
  `book_date` timestamp ,
  unique key (`order_number`)
);