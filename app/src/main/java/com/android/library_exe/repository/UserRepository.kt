package com.android.library_exe.repository
import com.android.library_exe.utlis.User
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions

class UserRepository {
    private val db = FirebaseFirestore.getInstance()

    fun addUser(user: User) {
        db.collection("users")
            .document(user.email)
            .set(user, SetOptions.merge())
    }
}