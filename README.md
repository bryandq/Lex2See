# Lex2See 📸📚 ứng dụng di động hỗ trợ học và ghi nhớ từ vựng tiếng Anh thông qua nhận diện hình ảnh, flashcard cá nhân hóa và Spaced Repetition System (SRS).

Snap to see, learn quickly 

Ứng dụng hướng đến nhóm người học bận rộn, đặc biệt là sinh viên, giúp biến các đồ vật xuất hiện trong đời sống hằng ngày thành nguồn từ vựng cá nhân có thể lưu, học và ôn tập lâu dài.

🔗 Project Links
📄 Product Documentation
Toàn bộ tài liệu phân tích và thiết kế sản phẩm được trình bày trên Notion:
👉 Lex2See Product Documentation: https://app.notion.com/p/Lex2See-ng-d-ng-h-c-t-v-ng-ti-ng-anh-th-ng-qua-h-nh-nh-3eb431aa20bc801aa751f2257bcfe9cc?source=copy_link
  
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

🧠 Product Positioning
Lex2See không định cạnh tranh trực tiếp với:
- Duolingo về curriculum và gamification.
- ELSA Speak về AI pronunciation.
- Quizlet về số lượng study sets.
Lex2See tập trung vào một hành vi khác:
Biến thế giới thực xung quanh người học thành vốn từ tiếng Anh cá nhân.

Value Proposition
Lex2See helps learners turn everyday objects into personalized English vocabulary and remember them through smart spaced review.

Product Tagline
Snap to see, learn quickly

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
🧪 MVP Scope
MVP của Lex2See tập trung vào core learning loop:
Authentication
      ↓
Camera Recognition
      ↓
Vocabulary Detail
      ↓
Save Vocabulary
      ↓
Personal Wordbook
      ↓
Flashcard
      ↓
SRS Review
      ↓
Learning Progress
Các chức năng như:
- Community
- Study Group
- Leaderboard
- Badge
- Shop
- Advanced Gamification
được định hướng cho các phiên bản sau.
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
🏗️ Architecture
Jetpack Compose UI
        ↓
     ViewModel
        ↓
    Repository
     ↙      ↘
Room        Firebase
Local       Cloud
  ↑           ↓
  └── WorkManager Sync ──┘
Object recognition flow:
CameraX
   ↓
YOLO / TensorFlow Lite
   ↓
Model Label
   ↓
Label Mapping
   ↓
Vocabulary
   ↓
Personal Wordbook
   ↓
Flashcard / SRS
📁 Project Structure
com.example.lex2see
│
├── app
├── data
│   ├── local
│   │   ├── dao
│   │   ├── entity
│   │   └── model
│   ├── remote
│   └── repository
├── domain
│   ├── model
│   └── repository
├── navigation
├── ui
│   ├── auth
│   ├── camera
│   ├── vocabulary
│   ├── flashcard
│   ├── review
│   ├── progress
│   └── home
└── worker
Cấu trúc thư mục có thể tiếp tục thay đổi trong quá trình phát triển.

🗄️ Main Data Entities
Các entity chính gồm:
- users
- vocabularies
- model_labels
- topics
- user_vocabularies
- user_vocabulary_topics
- detections
- detection_results
- study_sessions
- study_session_items
- daily_progress
- user_goals
- notification_settings
Chi tiết database design được trình bày tại:
👉 Database Documentation
🚀 Getting Started
1. Clone repository
git clone YOUR_GITHUB_REPOSITORY_URL
cd Lex2See
2. Open project
Mở project bằng Android Studio.
3. Sync Gradle
File
→ Sync Project with Gradle Files
4. Firebase configuration
Tạo Firebase Android App với package:
com.example.lex2see
Sau đó tải:
google-services.json
và đặt tại:
app/google-services.json
5. Run
- Kết nối thiết bị Android thật hoặc Emulator.
- Chọn configuration app.
- Nhấn Run ▶.
🔐 Environment & Security
Không commit các file hoặc giá trị nhạy cảm như:
local.properties
*.jks
*.keystore
service-account.json
private credentials
Nên kiểm tra .gitignore trước khi push source code.
📈 Product Metrics
Một số metric dự kiến được sử dụng để đánh giá MVP:
- First Scan Success Rate
- Scan → Save Conversion Rate
- Learning Session Completion Rate
- Review Completion Rate
- D1 Retention
- D7 Retention
- Vocabulary Review Accuracy
- 7-day Vocabulary Retention
North Star Metric
Weekly Retained Words
Số từ người dùng có thể nhớ đúng sau khi đã trải qua quá trình học và ôn tập trong tuần.

🗺️ Product Roadmap
Phase 1 — Core MVP
- Authentication
- Camera Recognition
- Vocabulary Detail
- Wordbook
- Flashcard
- SRS Review
- Progress
Phase 2 — Engagement
- Daily Goals
- Reminder
- Streak
- Badge
- Advanced Learning Statistics
Phase 3 — Social Learning
- Community
- Study Groups
- Leaderboard
- Challenges
Phase 4 — Personalization
- Advanced AI recommendations
- Learning difficulty adaptation
- Topic recommendations
- Personalized review strategy
🎨 Design
UI/UX được thiết kế trên Figma.
👉 Lex2See Figma Design
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
📚 Documentation
README trên GitHub tập trung vào phần kỹ thuật và tổng quan sản phẩm. Các tài liệu phân tích chi tiết được tách sang Notion:
Documentation	Link
Market Research	View on Notion
Product Requirements	View on Notion
User Research	View on Notion
Use Cases	View on Notion
Database Design	View on Notion
Product Roadmap	View on Notion


👨‍💻 Author
Doan Quoc Huy
Graduation Project — Lex2See
Product Research • UX Research • Android Development • AI-assisted Vocabulary Learning

📌 Project Status
🚧 Currently in development
Current focus:
Firebase Authentication
→ Firestore User Profile
→ Room ↔ Firestore Sync
→ CameraX
→ YOLO / TensorFlow Lite
→ Vocabulary Learning Flow
📄 License
This project is developed for educational and graduation-project purposes.
Please contact the author before reusing the source code, design, or project documentation.
