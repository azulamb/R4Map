package net.azulite.r4map;

import java.util.Random;

public class R4Map
{
	/*static public void main( String[] args )
	{
		R4Map map = new R4Map( 6, 6 );
		map.setCount( 10 );
		map.create();
		System.out.println( "[" + map.getBeginX() + "," + map.getBeginY() + "]=>[" + map.getEndX() + "," + map.getEndY() + "]" );
		map.setCount( 100 );
		map.create();
		System.out.println( "[" + map.getBeginX() + "," + map.getBeginY() + "]=>[" + map.getEndX() + "," + map.getEndY() + "]" );
		map.setCount();
		map.create();
		System.out.println( "[" + map.getBeginX() + "," + map.getBeginY() + "]=>[" + map.getEndX() + "," + map.getEndY() + "]" );
	}*/

	private int w, h, bx, by, ex, ey, count;
	private byte[][] map;
	private Random rnd;

	public R4Map( int w, int h )
	{
		this.w = w;
		this.h = h;
		map = new byte[ h ][];
		for ( int y = 0 ; y < h ; ++y )
		{
			map[ y ] = new byte[ w ];
		}
		ex = 0;
		ey = 0;
		setCount();
		setRandom( new Random() );
	}

	public void setRandom( Random rnd ){ this.rnd = rnd; }
	public void setCount(){ setCount( (int)(w * h * 0.7) - 1 ); }
	public void setCount( int count )
	{
		if ( w * h <= count ){ count = w * h - 1; }
		this.count = count;
	}
	public void create(){ create( ex, ey ); }
	public void create( int sx, int sy )
	{
		int x, y;
		bx = sx;
		by = sy;
		for ( y = 0 ; y < h ; ++y ){ for( x = 0 ; x < w ; ++x ){ map[ y ][ x ] = 0; } }

		byte[][] map = createLoadMap( sx, sy, baseLoadMap() );

		// Debug print
		//for (y=0;y<h*2+1;++y){for (x=0;x<w*2+1;++x){System.out.print("["+map[y][x]+"]");}System.out.println();}

		for ( y = 0 ; y < h ; ++y )
		{
			for ( x = 0 ; x < w ; ++x )
			{
				if ( map[ y * 2 + 1 ][ x * 2 + 1 ] == 0 )
				{
					this.map[ y ][ x ] = 0;
				} else
				{
					this.map[ y ][ x ] = (byte)( (map[ y * 2 + 1 ][ x * 2 ]*8) | (map[ y * 2 + 2 ][ x * 2 + 1 ]*4) | (map[ y * 2 + 1 ][ x * 2 + 2 ]*2) | (map[ y * 2 ][ x * 2 + 1 ]) );
				}
				// Debug print.
				//System.out.print( "[" + chip[ y ][ x ] + "]");
			}
			// Debug print.
			//System.out.print( "\n" );
		}
	}
	private byte[][] baseLoadMap()
	{
		int x, y, H, W;
		W = w * 2 + 1;
		H = h * 2 + 1;
		byte[][] map = new byte[ H ][];
		for( y = 0 ; y < H ; ++y )
		{
			map[ y ] = new byte[ W ];
			if ( y == 0 || y + 1 == H )
			{
				for( x = 0 ; x < W ; ++x ){ map[ y ][ x ] = 2; }
			} else
			{
				map[ y ][ 0 ] = map[ y ][ W - 1 ] = 2;
			}
		}
		return map;
	}
	private byte[][] createLoadMap( int sx, int sy, byte[][] map )
	{
		int px, py;
		px = sx * 2 + 1;
		py = sy * 2 + 1;
		map[ py ][ px ] = 1;
		int count = this.count;

		do
		{
			switch( rnd.nextInt( 4 ) )
			{
			case 0:
				if ( map[ py - 1 ][ px ] == 2 ){ break; }
				map[ --py ][ px ] = 1;
				if ( map[ --py ][ px ] == 0 ){ --count; }
				map[ py ][ px ] = 1;
				break;
			case 1:
				if ( map[ py ][ px + 1 ] == 2 ){ break; }
				map[ py ][ ++px ] = 1;
				if ( map[ py ][ ++px ] == 0 ){ --count; }
				map[ py ][ px ] = 1;
				break;
			case 2:
				if ( map[ py + 1 ][ px ] == 2 ){ break; }
				map[ ++py ][ px ] = 1;
				if ( map[ ++py ][ px ] == 0 ){ --count; }
				map[ py ][ px ] = 1;
				break;
			case 3:
				if ( map[ py ][ px - 1 ] == 2 ){ break; }
				map[ py ][ --px ] = 1;
				if ( map[ py ][ --px ] == 0 ){ --count; }
				map[ py ][ px ] = 1;
				break;
			}
		} while ( 0 < count );
		ex = px / 2;
		ey = py / 2;
		py = map[ 0 ].length - 1;
		for ( px = 0 ; px < map.length ; ++px ){ map[ 0 ][ px ] = map[ py ][ px ] = 0; }
		px = map.length - 1;
		for ( py = 0 ; py < map[ 0 ].length ; ++py ){ map[ py ][ 0 ] = map[ py ][ px ] = 0; }
		return map;
	}

	public byte[][] getMap(){ return map; }
	public int getBeginX(){ return bx; }
	public int getBeginY(){ return by; }
	public int getEndX(){ return ex; }
	public int getEndY(){ return ey; }
}
