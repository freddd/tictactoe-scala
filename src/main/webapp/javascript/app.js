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
    x: 'X',
    mode: 'AI',
    board: [
        [, , ],
        [, , ],
        [, , ]
    ],

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
    drawSymbol: function (x, y, symbol) {

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

        if (symbol === FUNCTIONS.x) {
            context.moveTo(beginX, beginY);
            context.lineTo(endX, endY);

            context.moveTo(beginX, endY);
            context.lineTo(endX, beginY);
        } else {
            context.arc(beginX + ((endX - beginX) / 2), beginY + ((endY - beginY) / 2), (endX - beginX) / 2, 0, Math.PI * 2, true);
        }

        FUNCTIONS.board[x][y] = symbol
        context.stroke();
        context.closePath();
    },

    ajaxPlayerMove: function (x, y) {

    },

    ajaxGetStartMove: function () {

    },

    isValidMove: function (x, y) {
        if (x <= 2 && y <= 2) {
            if (FUNCTIONS.board[x][y] == null) {
                return true;
            }
        }
        return false;
    },

    handleEvents: function () {
        $('#board').click(function (e) {
            var y = Math.floor(e.clientY / (FUNCTIONS.height / 3));
            var x = Math.floor(e.clientX / (FUNCTIONS.width / 3));

            // Check if the move is valid (i.e. there is no other symbols in that square)
            if (FUNCTIONS.isValidMove(x, y)) {
                // First draw X on screen
                FUNCTIONS.drawSymbol(x, y, FUNCTIONS.x);

                // Call server with the move made by the player
                FUNCTIONS.ajaxPlayerMove(x, y);
            } else {
                alert("Not a valid move, there is already a symbol in the square");
            }
        });
    }
}


INIT = {
    init: function () {
        // Set mode
        FUNCTIONS.mode = 'AI'

        // Draw board
        FUNCTIONS.drawBoard();

        // Decide who starts
        var xStarts = Math.floor(Math.random() * 2) == 1

        if (!xStarts && FUNCTIONS.mode === 'AI') {
            FUNCTIONS.ajaxGetStartMove();
        }

        // Handling click events from user
        FUNCTIONS.handleEvents();
    }
}


$(document).ready(INIT.init);