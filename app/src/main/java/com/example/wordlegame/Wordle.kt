package com.example.wordlegame

import android.content.Context
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import kotlin.random.Random

class Wordlet(
    private val sizeOfWord: Int,
    context: Context
) {
    private val numOfTries = 6
    private var numOfGuesses = 0
    private val guesses = arrayOfNulls<String>(numOfTries)
    private val words = HashMap<String, String>()
    private val appContext = context.applicationContext

    var randomWord: String = ""
        private set

    init {
        when (sizeOfWord) {
            6 -> parseFile("six_letter_words.txt")
            5 -> parseFile("five_letter_words.txt")
            4 -> parseFile("four_letter_words.txt")
        }
    }

    val wordsList: HashMap<String, String>
        get() = words

    fun addGuess(word: String) {
        if (numOfGuesses < numOfTries) {
            guesses[numOfGuesses] = word
            numOfGuesses++
        }
    }

    fun getNumOfGuesses(): Int {
        return numOfTries
    }

    fun setRandomWord() {
        if (words.isEmpty()) {
            randomWord = ""
            return
        }

        randomWord = words.keys.elementAt(Random.nextInt(words.size))
    }

    fun validWord(word: String, sizeOfWord: Int): Boolean {
        return word.length == sizeOfWord && words.containsKey(word)
    }

    fun isWord(word: String): Boolean {
        return word == randomWord
    }

    fun containsLetter(guess: String, randomWord: String): Boolean {
        for (i in guess.indices) {
            for (j in randomWord.indices) {
                if (guess[i] == randomWord[j]) return true
            }
        }
        return false
    }

    fun getLetters(guess: String): String {
        val containedLetters = StringBuilder()

        for (i in 0 until sizeOfWord) {
            for (j in 0 until sizeOfWord) {
                if (containsLetter(guess[i].toString(), containedLetters.toString())) {
                    break
                }

                if (guess[i] == randomWord[j]) {
                    containedLetters.append(guess[i])
                }
            }
        }

        return containedLetters.toString()
    }

    private fun parseFile(fileName: String) {
        try {
            BufferedReader(
                InputStreamReader(appContext.assets.open(fileName))
            ).use { reader ->
                var line: String?
                while (reader.readLine().also { line = it } != null) {
                    line?.let {
                        words[it] = it
                    }
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}