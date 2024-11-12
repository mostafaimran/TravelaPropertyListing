package com.travela.propertylisting.ui.screen.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.travela.propertylisting.datamodel.ext.getLogDateFormat
import com.travela.propertylisting.ui.screen.LogData

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LogSheetLayout(logList: List<LogData>, onDismiss: () -> Unit) {
    ModalBottomSheet(
        onDismissRequest = {
            onDismiss()
        },
        contentColor = Color.LightGray,
        containerColor = Color.Gray
    ) {
        LazyColumn(modifier = Modifier.padding(16.dp)) {
            items(logList.size) { index ->
                LogItem(logList[index])
                Spacer(modifier = Modifier.padding(8.dp))
            }
        }
    }
}

@Composable
fun LogItem(logData: LogData) {
    Column(
        modifier = Modifier
            .background(color = Color.White, shape = RoundedCornerShape(4.dp))
            .padding(8.dp)
    ) {
        Text(text = logData.date.getLogDateFormat(), color = Color.Black, fontWeight = FontWeight.Bold)
        Text(text = logData.log, color = Color.Black, fontWeight = FontWeight.Normal)
    }
}