package com.example.crudroomtutorial.room

import androidx.room.*

@Dao
interface NoteDao {

    @Insert
    fun addNote(note: Note)

    @Update
    fun updateNote(note: Note)

    @Delete
    fun deleteNote(note: Note)

    @Query("SELECT * FROM")
    fun getNotes(note: Note):List<Note>
}