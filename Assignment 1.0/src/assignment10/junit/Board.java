package assignment10.junit;


public class Board {
	public static Piece white_king = new PieceKing();
	public static Piece white_queen = new PieceQueen();	
	public static Piece white_bishop_1 = new PieceBishop();
	public static Piece white_bishop_2 = new PieceBishop();
	public static Piece white_knight_1 = new PieceKnight();
	public static Piece white_knight_2 = new PieceKnight();
	public static Piece white_rook_1 = new PieceRook();
	public static Piece white_rook_2 = new PieceRook();
	public static Piece white_pawn_1 = new PiecePawn();
	public static Piece white_pawn_2 = new PiecePawn();
	public static Piece white_pawn_3 = new PiecePawn();
	public static Piece white_pawn_4 = new PiecePawn();
	public static Piece white_pawn_5 = new PiecePawn();
	public static Piece white_pawn_6 = new PiecePawn();
	public static Piece white_pawn_7 = new PiecePawn();
	public static Piece white_pawn_8 = new PiecePawn();
	public static Piece black_king = new PieceKing();
	public static Piece black_queen = new PieceQueen();	
	public static Piece black_bishop_1 = new PieceBishop();
	public static Piece black_bishop_2 = new PieceBishop();
	public static Piece black_knight_1 = new PieceKnight();
	public static Piece black_knight_2 = new PieceKnight();
	public static Piece black_rook_1 = new PieceRook();
	public static Piece black_rook_2 = new PieceRook();
	public static Piece black_pawn_1 = new PiecePawn();
	public static Piece black_pawn_2 = new PiecePawn();
	public static Piece black_pawn_3 = new PiecePawn();
	public static Piece black_pawn_4 = new PiecePawn();
	public static Piece black_pawn_5 = new PiecePawn();
	public static Piece black_pawn_6 = new PiecePawn();
	public static Piece black_pawn_7 = new PiecePawn();
	public static Piece black_pawn_8 = new PiecePawn();
	protected int size;	//size of board
	protected int num_alive=32; //num of pieces remained
	protected int step = 0;//how many steps
	protected int win = 0;//not win yet
	protected int[][] board;
	protected Piece[] array_p;//array of 32 pieces 
	protected boolean turn=false;
	
    /*move a piece*/
	public int Board_move(int x, int y, Piece p)
	{
		/*check if p can move and if there's any leap-over*/
		if(Board_can_move(x, y, p)==false)
		{	
			System.out.println("Invalid move_1");
			return 0;
		}
		/*check if a pawn can move*/
		if(p.can_move(x, y, turn)==2)
		{ 
			if(board[x][y]==0)//nothing to capture
			{
				System.out.println("Invalid move_2");
				return 0;
			}
			else
			{	//System.out.println("Capture ");
				//array_p[board[x][y]].print();
				board[p.x][p.y] = 0; //pick up the piece from the board
				array_p[board[x][y]].remove(); //set piece status to false
				p.move(x, y); //set piece to right position
				board[x][y] = p.id(); //put it to destination
				num_alive--; //number of pieces --	
				step++;
				turn = !turn;
				if(win())
					return 1;
				return 0;
			}
		}
		/*capture another piece*/
		if(board[x][y]!=0)
		{
			//System.out.println("Capture ");
			//array_p[board[x][y]].print();
			board[p.x][p.y] = 0; //pick up the piece from the board
			array_p[board[x][y]].remove(); //set piece status to false
			p.move(x, y); //set piece to right position
			board[x][y] = p.id(); //put it to destination
			num_alive--; //number of pieces --	
			step++;
			turn = !turn;
		}
		/*only move the piece*/
		else
		{
			board[p.x][p.y] = 0;
			p.move(x, y);
			board[x][y] = p.id();
			step++;
			turn = !turn;
		}
		/*if one of the king dies*/
		if(win())
			return 1;
		return 0;
	}
	
	/*helper function of Board_move*/
	public boolean Board_can_move(int x, int y, Piece p)
	{
		if(p.out_of_board(x, y, size))
			return false;
		if(p.can_move(x, y, turn)==0)
			return false;
		if(p.type()==3)//knight
			return true;//no leap_over
		if(p.leap_over(x, y, white_king)||p.leap_over(x, y, white_queen)||
		   p.leap_over(x, y, white_pawn_1)||p.leap_over(x, y, white_pawn_2)||
		   p.leap_over(x, y, white_pawn_3)||p.leap_over(x, y, white_pawn_4)||
		   p.leap_over(x, y, white_pawn_5)||p.leap_over(x, y, white_pawn_6)||
		   p.leap_over(x, y, white_pawn_7)||p.leap_over(x, y, white_pawn_8)||
		   p.leap_over(x, y, white_rook_1)||p.leap_over(x, y, white_rook_2)||
		   p.leap_over(x, y, white_knight_1)||p.leap_over(x, y, white_knight_2)||
		   p.leap_over(x, y, white_bishop_1)||p.leap_over(x, y, white_bishop_2)||
		   p.leap_over(x, y, black_king)||p.leap_over(x, y, black_queen)||
		   p.leap_over(x, y, black_pawn_1)||p.leap_over(x, y, black_pawn_2)||
		   p.leap_over(x, y, black_pawn_3)||p.leap_over(x, y, black_pawn_4)||
		   p.leap_over(x, y, black_pawn_5)||p.leap_over(x, y, black_pawn_6)||
		   p.leap_over(x, y, black_pawn_7)||p.leap_over(x, y, black_pawn_8)||
		   p.leap_over(x, y, black_rook_1)||p.leap_over(x, y, black_rook_2)||
		   p.leap_over(x, y, black_knight_1)||p.leap_over(x, y, black_knight_2)||
		   p.leap_over(x, y, black_bishop_1)||p.leap_over(x, y, black_bishop_2))
		{
			System.out.println("Over-Leap!");
			return false;
		}
		return true;
	}
	
	public boolean win()
	{
		if (black_king.status == false)
		{
			System.out.println("White Wins!");
			return true;
		}
		if(white_king.status == false)
		{
			System.out.println("Black Wins!");
			return true;
		}
		return false;
	}
	
	/*initialize the board*/
	public Board(int size) {
		this.size = size;
		num_alive = 32;
		board = new int[size][size];
		int count = 0;
		for(int i=1; i>=0; i--)
		{
			for(int j=0; j<size; j++)
			{	
				board[j][i] = count;
				count++;
			}
		}//black:0-7 pawn, 8,15-rook, 9,14-knight, 10,13-bishop, 11-queen, 12-king
		for(int i=2; i<size-2;i++)
		{
			for(int j=0; j<size;j++)
				board[j][i] = 0;
		}//All other part is 0
		for(int i=size-2; i<size; i++)
		{
			for(int j=0; j<size; j++)
			{	
				board[j][i] = count;
				count++;
			}
		}//black:16-23 pawn, 24,31-rook, 25,30-knight, 26,29-bishop, 27-queen, 28-king
		array_p = new Piece[32];
		array_p[0] = black_pawn_1;array_p[0].initial(0, 1, true, true, 0);
		array_p[1] = black_pawn_2;array_p[1].initial(1, 1, true, true, 1);
		array_p[2] = black_pawn_3;array_p[2].initial(2, 1, true, true, 2);
		array_p[3] = black_pawn_4;array_p[3].initial(3, 1, true, true, 3);
		array_p[4] = black_pawn_5;array_p[4].initial(4, 1, true, true, 4);
		array_p[5] = black_pawn_6;array_p[5].initial(5, 1, true, true, 5);
		array_p[6] = black_pawn_7;array_p[6].initial(6, 1, true, true, 6);
		array_p[7] = black_pawn_8;array_p[7].initial(7, 1, true, true, 7);
		array_p[8] = black_rook_1;array_p[8].initial(0, 0, true, true, 8);
		array_p[9] = black_knight_1;array_p[9].initial(1, 0, true, true, 9);
		array_p[10] = black_bishop_1;array_p[10].initial(2, 0, true, true, 10);
	    array_p[11] = black_queen;array_p[11].initial(3, 0, true, true, 11);
	    array_p[12] = black_king;array_p[12].initial(4, 0, true, true, 12);
	    array_p[13] = black_bishop_2;array_p[13].initial(5, 0, true, true, 13);
	    array_p[14] = black_knight_2;array_p[14].initial(6, 0, true, true, 14);
	    array_p[15] = black_rook_2;array_p[15].initial(7, 0, true, true, 15);
	    array_p[16] = white_pawn_1;array_p[16].initial(0, 6, false, true, 16);
	    array_p[17] = white_pawn_2;array_p[17].initial(1, 6, false, true, 17);
	    array_p[18] = white_pawn_3;array_p[18].initial(2, 6, false, true, 18);
	    array_p[19] = white_pawn_4;array_p[19].initial(3, 6, false, true, 19);
	    array_p[20] = white_pawn_5;array_p[20].initial(4, 6, false, true, 20);
	    array_p[21] = white_pawn_6;array_p[21].initial(5, 6, false, true, 21);
	    array_p[22] = white_pawn_7;array_p[22].initial(6, 6, false, true, 22);
	    array_p[23] = white_pawn_8;array_p[23].initial(7, 6, false, true, 23);
	    array_p[24] = white_rook_1;array_p[24].initial(0, 7, false, true, 24);
		array_p[25] = white_knight_1;array_p[25].initial(1, 7, false, true, 25);
		array_p[26] = white_bishop_1;array_p[26].initial(2, 7, false, true, 26);
	    array_p[27] = white_queen;array_p[27].initial(3, 7, false, true, 27);
	    array_p[28] = white_king;array_p[28].initial(4, 7, false, true, 28);
	    array_p[29] = white_bishop_2;array_p[29].initial(5, 7, false, true, 29);
	    array_p[30] = white_knight_2;array_p[30].initial(6, 7, false, true, 30);
	    array_p[31] = white_rook_2;array_p[31].initial(7, 7, false, true, 31);  
	}
}