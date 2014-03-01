/**
 * Created by fred on 3/1/14.
 */













FUNCTIONS = {
    xBoard: 0,
    oBoard: 0,
    begin: true,
    context: null,
    width: 0,
    height: 0,

    drawBoard: function () {
        var board = document.getElementById('board');

        FUNCTIONS.width = board.width;
        FUNCTIONS.height = board.height;
        FUNCTIONS.context = board.getContext('2d');

        FUNCTIONS.context.beginPath();
        FUNCTIONS.context.strokeStyle = '#000';
        FUNCTIONS.context.lineWidth = 4;

        FUNCTIONS.context.moveTo((FUNCTIONS.width / 3), 0);
        FUNCTIONS.context.lineTo((FUNCTIONS.width / 3), FUNCTIONS.height);

        FUNCTIONS.context.moveTo((FUNCTIONS.width / 3) * 2, 0);
        FUNCTIONS.context.lineTo((FUNCTIONS.width / 3) * 2, FUNCTIONS.height);

        FUNCTIONS.context.moveTo(0, (FUNCTIONS.height / 3));
        FUNCTIONS.context.lineTo(FUNCTIONS.width, (FUNCTIONS.height / 3));

        FUNCTIONS.context.moveTo(0, (FUNCTIONS.height / 3) * 2);
        FUNCTIONS.context.lineTo(FUNCTIONS.width, (FUNCTIONS.height / 3) * 2);

        FUNCTIONS.context.stroke();
        FUNCTIONS.context.closePath();
    },
    drawX: function (x, y) {

        var context = FUNCTIONS.context
        var width = FUNCTIONS.width
        var height = FUNCTIONS.height

        context.beginPath();

        context.strokeStyle = '#ff0000';
        context.lineWidth = 4;

        var offsetX = (width / 3) * 0.1;
        var offsetY = (height / 3) * 0.1;

        var beginX = x * (width / 3) + offsetX;
        var beginY = y * (height / 3) + offsetY;

        var endX = (x + 1) * (width / 3) - offsetX * 2;
        var endY = (y + 1) * (height / 3) - offsetY * 2;

        context.moveTo(beginX, beginY);
        context.lineTo(endX, endY);

        context.moveTo(beginX, endY);
        context.lineTo(endX, beginY);

        context.stroke();
        context.closePath();
    },
    drawO: function (x, y) {

        var context = FUNCTIONS.context
        var width = FUNCTIONS.width
        var height = FUNCTIONS.height

        context.beginPath();

        context.strokeStyle = '#0000ff';
        context.lineWidth = 4;

        var offsetX = (width / 3) * 0.1;
        var offsetY = (height / 3) * 0.1;

        var beginX = x * (width / 3) + offsetX;
        var beginY = y * (height / 3) + offsetY;

        var endX = (x + 1) * (width / 3) - offsetX * 2;
        var endY = (y + 1) * (height / 3) - offsetY * 2;

        context.arc(beginX + ((endX - beginX) / 2), beginY + ((endY - beginY) / 2), (endX - beginX) / 2, 0, Math.PI * 2, true);

        context.stroke();
        context.closePath();
    },

    handleEvents:function(){
        $('#board').click(function() {
            alert( "Handler for .click() called." );
        });
    }
}


INIT = {
    init: function () {
        FUNCTIONS.drawBoard()
        FUNCTIONS.handleEvents()
    }
}


$(document).ready(INIT.init);