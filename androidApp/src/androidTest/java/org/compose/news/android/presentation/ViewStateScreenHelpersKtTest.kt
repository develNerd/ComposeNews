package org.compose.news.android.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import org.compose.news.android.ComposeNewsTheme
import org.compose.news.android.contants.TestDoubleTags.CONTENT_SUCCESS_TEST_TAG
import org.compose.news.android.contants.TestDoubleTags.EMPTY_STATE_TEST_TAG
import org.compose.news.android.contants.TestDoubleTags.ERROR_RETRY_TEST_TAG
import org.compose.news.android.contants.TestDoubleTags.PROGRESS_TEST_TAG
import org.compose.news.data.model.ArticlesItem
import org.compose.news.data.model.Source
import org.junit.Assert.*

import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ViewStateScreenHelpersKtTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val articles = mutableListOf(
        ArticlesItem("2022-12-05T08:01:47","Sophie Kiderlin","https://apnews.com/article",
            "Description 1",
            Source("CNBC","CNBC1"),"Title 1","https://apnews.com/article","Content 1"),
        ArticlesItem("2010-06-01T22:19:44","Sophie Kiderlin","https://apnews.com/article",
            "Description 2",
            Source("CNBC","CNBC1"),"Title 1","https://apnews.com/article","Content 1"),
        ArticlesItem("2010-06-01T22:19:44","Sophie Kiderlin","https://apnews.com/article",
            "Description 3",
            Source("CNBC","CNBC1"),"Title 1","https://apnews.com/article","Content 1"),
        ArticlesItem("2010-06-01T22:19:44","Sophie Kiderlin","https://apnews.com/article",
            "Description 4",
            Source("CNBC","CNBC1"),"Title 1","https://apnews.com/article","Content 1"),
        ArticlesItem("2010-06-01T22:19:44","Sophie Kiderlin","https://apnews.com/article",
            "Description 4",
            Source("CNBC","CNBC1"),"Title 1","https://apnews.com/article","Content 1")
    )

    @Test
    fun testSuccessGenericListContent() {
        composeTestRule.setContent {

            GenericListViewRenderer(articles,loadComplete = true, onRetryClicked = { }) {
                Box(modifier = Modifier.semantics {
                    testTag = CONTENT_SUCCESS_TEST_TAG
                    contentDescription = CONTENT_SUCCESS_TEST_TAG
                }){
                    Text(CONTENT_SUCCESS_TEST_TAG)
                }
            }
        }
        composeTestRule.onNodeWithText(CONTENT_SUCCESS_TEST_TAG,true).assertIsDisplayed()
        composeTestRule.onNode(hasContentDescription(CONTENT_SUCCESS_TEST_TAG),false).assertIsDisplayed()
        composeTestRule.onNode(hasTestTag(CONTENT_SUCCESS_TEST_TAG)).assertExists()
    }





    @Test
    fun testErrorForGenericListContent() {
        composeTestRule.setContent {
            ComposeNewsTheme {
                GenericListViewRenderer(emptyList<ArticlesItem>(),loadComplete = true,isError = true, onRetryClicked = { }) {
                    Box(modifier = Modifier.testTag(CONTENT_SUCCESS_TEST_TAG))
                }
            }
        }
        composeTestRule.onNode(hasContentDescription(ERROR_RETRY_TEST_TAG),false).assertIsDisplayed()
    }

    @Test
    fun testLoadingForGenericListContent() {
        composeTestRule.setContent {
            ComposeNewsTheme {
                GenericListViewRenderer<ArticlesItem>(null,loadComplete = false,isError = false, onRetryClicked = { }) {
                    Box(modifier = Modifier.testTag(CONTENT_SUCCESS_TEST_TAG))
                }
            }
        }
        composeTestRule.onNode(hasContentDescription(PROGRESS_TEST_TAG),false).assertIsDisplayed()
    }

    @Test
    fun testForEmptyGenericListContent() {
        composeTestRule.setContent {
            ComposeNewsTheme {
                GenericListViewRenderer<ArticlesItem>(emptyList(),loadComplete = true,isError = false, onRetryClicked = { }) {
                    Box(modifier = Modifier.testTag(CONTENT_SUCCESS_TEST_TAG))
                }
            }
        }
        composeTestRule.onNode(hasContentDescription(EMPTY_STATE_TEST_TAG),false).assertIsDisplayed()
    }



}