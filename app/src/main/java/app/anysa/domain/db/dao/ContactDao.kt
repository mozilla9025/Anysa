package app.anysa.domain.db.dao

import androidx.room.*
import app.anysa.domain.pojo.User
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
abstract class ContactDao {

    @Query("SELECT * FROM $TABLE_NAME")
    abstract fun getAll(): Flowable<List<User>>

    @Query("SELECT * FROM $TABLE_NAME WHERE userName LIKE :query")
    abstract fun getByName(query: String): Flowable<List<User>>

    fun getByNameStartWith(query: String) = getByName("%$query%")

    @Query("SELECT * FROM $TABLE_NAME WHERE id = :userId")
    abstract fun getUserById(userId: Int): Single<User>

    @Query("SELECT * FROM $TABLE_NAME WHERE id = :userId")
    abstract fun getUserByIdRaw(userId: Int): User?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun rawInsert(user: List<User>)

    @Delete
    abstract fun rawDelete(user: User)

    @Query("DELETE FROM $TABLE_NAME")
    abstract fun rawClear()

    fun insert(user: List<User>) = Completable.fromAction { rawInsert(user) }
    fun delete(user: User) = Completable.fromAction { rawDelete(user) }
    fun clear() = Completable.fromAction { rawClear() }

    fun rewriteTable(user: List<User>) = clear().andThen(insert(user))
    fun rawRewriteTable(user: List<User>) {
        rawClear()
        rawInsert(user)
    }

    companion object {
        const val TABLE_NAME = "user"
    }
}