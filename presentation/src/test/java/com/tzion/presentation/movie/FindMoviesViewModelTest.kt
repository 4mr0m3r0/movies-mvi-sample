package com.tzion.presentation.movie

class FindMoviesViewModelTest {

    //TODO: For the next PR
//    @get:Rule
//    var instantTaskExecutorRule = InstantTaskExecutorRule()
//    var findMoviesUseCase = mock<FindMoviesUseCase>()
//    var movieMapper = mock<PresentationMovieMapper>()
//    var movieViewModel = FindMoviesViewModel(findMoviesUseCase, movieMapper)
//
//    @Captor
//    val captor = argumentCaptor<DisposableSingleObserver<List<Movie>>>()
//
//    @Test
//    fun fetchMoviesExecutesUseCase() {
//        val param = DataFactory.randomString()
//        movieViewModel.findMoviesByText(param)
//
//        verify(findMoviesUseCase, times(1)).execute(any(), eq(FindMoviesUseCase.Params(param)))
//    }
//
//    @Test
//    fun fetchMoviesReturnsSuccess() {
//        val param = DataFactory.randomString()
//        val movies = MovieFactory.makeMovieList(2)
//        val movieViews = MovieFactory.makeMovieViewList(2)
//        stubMovieMapperToView(movieViews[0], movies[0])
//        stubMovieMapperToView(movieViews[1], movies[1])
//
//        movieViewModel.findMoviesByText(param)
//        verify(findMoviesUseCase).execute(captor.capture(), eq(FindMoviesUseCase.Params(param)))
//        captor.firstValue.onSuccess(movies)
//        assertEquals(ResourceState.SUCCESS, movieViewModel.getMoviesLiveData().value?.status)
//    }
//
//    @Test
//    fun fetchMoviesReturnsData() {
//        val param = DataFactory.randomString()
//        val movies = MovieFactory.makeMovieList(2)
//        val movieViews = MovieFactory.makeMovieViewList(2)
//        stubMovieMapperToView(movieViews[0], movies[0])
//        stubMovieMapperToView(movieViews[1], movies[1])
//
//        movieViewModel.findMoviesByText(param)
//        verify(findMoviesUseCase).execute(captor.capture(), eq(FindMoviesUseCase.Params(param)))
//        captor.firstValue.onSuccess(movies)
//        assertEquals(movieViews, movieViewModel.getMoviesLiveData().value?.data)
//    }
//
//    @Test
//    fun fetchMoviesReturnsError() {
//        val param = DataFactory.randomString()
//        movieViewModel.findMoviesByText(param)
//        verify(findMoviesUseCase).execute(captor.capture(), eq(FindMoviesUseCase.Params(param)))
//        captor.firstValue.onError(RuntimeException())
//        assertEquals(ResourceState.ERROR, movieViewModel.getMoviesLiveData().value?.status)
//    }
//
//    @Test
//    fun fetchMoviesReturnsMessageForError() {
//        val param = DataFactory.randomString()
//        val errorMessage = DataFactory.randomString()
//        movieViewModel.findMoviesByText(param)
//        verify(findMoviesUseCase).execute(captor.capture(), eq(FindMoviesUseCase.Params(param)))
//        captor.firstValue.onError(RuntimeException(errorMessage))
//        assertEquals(errorMessage, movieViewModel.getMoviesLiveData().value?.message)
//    }
//
//    private fun stubMovieMapperToView(movieView: PresentationMovie, movie: Movie) {
//        whenever(movieMapper.mapToPresentation(movie)).thenReturn(movieView)
//    }


}