# Lex2See 📸📚 ứng dụng di động hỗ trợ học và ghi nhớ từ vựng tiếng Anh thông qua nhận diện hình ảnh, flashcard cá nhân hóa và Spaced Repetition System (SRS).

Snap to see, learn quickly 

Ứng dụng hướng đến nhóm người học bận rộn, đặc biệt là sinh viên, giúp biến các đồ vật xuất hiện trong đời sống hằng ngày thành nguồn từ vựng cá nhân có thể lưu, học và ôn tập lâu dài.

🔗 Project Links

📄 Product Documentation

Toàn bộ tài liệu phân tích và thiết kế sản phẩm được trình bày trên Notion:

👉 Lex2See Product Documentation: 
https://app.notion.com/p/Lex2See-ng-d-ng-h-c-t-v-ng-ti-ng-anh-th-ng-qua-h-nh-nh-3eb431aa20bc801aa751f2257bcfe9cc?source=copy_link
  
💻 Source Code

Mã nguồn của dự án được quản lý trên GitHub này

🎯 Problem

Người học tiếng Anh thường gặp các vấn đề:

- Học từ mới nhưng nhanh quên.
- Biết nghĩa nhưng khó sử dụng từ trong ngữ cảnh thực tế.
- Gặp đồ vật quen thuộc nhưng không biết tên tiếng Anh.
- Khó duy trì thói quen học từ vựng mỗi ngày.
- Việc tự tạo flashcard hoặc ghi chép từ mới mất thời gian.

Khảo sát ban đầu với nhóm sinh viên mục tiêu cho thấy các pain point nổi bật gồm:

- 76.7% cho biết thường học từ mới nhưng nhanh quên.
- 70% gặp khó khăn khi sử dụng từ trong ngữ cảnh.
- 70% khó duy trì thói quen học.
- 90% cho biết có thể sử dụng ứng dụng khi gặp một đồ vật mà không biết tên tiếng Anh.

💡 Solution

Lex2See xây dựng một learning loop khép kín:

See -> Recognize -> Understand -> Save -> Learn -> Review -> Remember

Thay vì chỉ nhận diện vật thể, Lex2See tiếp tục biến kết quả nhận diện thành nội dung học tập cá nhân.

Ví dụ:
Camera nhận diện "bottle"
Hiển thị:
- Word: bottle
- IPA
- Nghĩa tiếng Việt
- Audio
- Example sentence
Sau đó: 
Save to Personal Wordbook -> Flashcard -> SRS Review

✨ Core Features

📷 AI Object Recognition

- Nhận diện vật thể thông qua camera.
- Hiển thị tên tiếng Anh tương ứng.
- Cho phép người dùng xác nhận hoặc sửa kết quả nhận diện.
- Ưu tiên xử lý on-device để giảm độ trễ và tăng quyền riêng tư.

📖 Vocabulary Details

Mỗi từ có thể bao gồm:
- English word
- Vietnamese meaning
- IPA
- Part of speech
- Audio pronunciation
- Example sentence
- Example translation

⭐ Personal Wordbook

- Lưu từ từ camera.
- Tìm kiếm và lọc từ.
- Theo dõi trạng thái học.
- Hạn chế duplicate vocabulary.

🃏 Flashcard Learning

- Học từ trong các phiên ngắn.
- Hỗ trợ thao tác swipe.
- Ghi nhận từ đã nhớ / chưa nhớ.
- Ưu tiên lại các từ chưa nhớ.

🔁 Spaced Repetition

- Tự động lên lịch ôn tập.
- Điều chỉnh lịch dựa trên kết quả học.
- Ưu tiên từ người dùng thường quên.
- Theo dõi first-try correctness.

📊 Learning Progress
- Số từ đã học.
- Số từ cần ôn.
- Review accuracy.
- Daily progress.
- Streak và learning goal.

🔔 Smart Reminder
- Nhắc người dùng khi có từ đến hạn ôn.
- Hỗ trợ mục tiêu học hằng ngày.

📵 Offline-first Learning

- Wordbook hoạt động offline.
- Flashcard và SRS review hoạt động offline.
- Dữ liệu được đồng bộ khi kết nối Internet trở lại.

👤 Target User

Primary Persona
Sinh viên 18–22 tuổi
Đặc điểm:
- Có trình độ tiếng Anh cơ bản đến trung cấp.
- Thường xuyên sử dụng smartphone.
- Chỉ có khoảng 10–30 phút cho mỗi lần học.
- Muốn cải thiện vốn từ phục vụ học tập, chứng chỉ và công việc.
- Thường học từ nhưng nhanh quên.
- Khó duy trì thói quen học lâu dài.

🛠️ Tech Stack

Android
- Kotlin
- Android Studio
- Jetpack Compose
- MVVM
- Navigation Compose

Local Data
- Room Database

Cloud
- Firebase Authentication
- Cloud Firestore
- Firebase Storage
- Firebase Cloud Messaging

Background Sync
- WorkManager

AI / Computer Vision
- CameraX
- TensorFlow Lite
- YOLO-based object detection model

│   ├── flashcard
│   ├── review
│   ├── progress
│   └── home
└── worker
Cấu trúc thư mục có thể tiếp tục thay đổi trong quá trình phát triển.



👉 Lex2See Figma Design: 
https://www.figma.com/design/CKzNwgGyBGHpcs3ZMzC2LS/VOCAB-APP?node-id=1-2&t=BRRVPcWBhm8FRLtY-1

Một số màn hình chính:
- Splash Screen
- Login
- Register
- Home
- Camera Recognition
- Vocabulary Detail
- Personal Wordbook
- Flashcard
- Review
- Learning Progress


👨‍💻 Author

Doan Quoc Huy

Graduation Project — Lex2See

Product Research • UX Research • Android Development • AI-assisted Vocabulary Learning

📌 Project Status

🚧 Currently in development
